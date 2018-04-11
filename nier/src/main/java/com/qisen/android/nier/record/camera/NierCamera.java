/*
 * Created by qisen (woaitqs@gmail.com)
 * Copyright (c) 2018.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.qisen.android.nier.record.camera;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.support.annotation.Nullable;

import com.qisen.android.nier.C;
import com.qisen.android.nier.util.Logger;
import com.qisen.android.nier.util.Utils;

import java.io.IOException;
import java.util.List;

import static android.hardware.Camera.Parameters.PREVIEW_FPS_MAX_INDEX;
import static android.hardware.Camera.Parameters.PREVIEW_FPS_MIN_INDEX;

/**
 * the only camera instance to preview, record and so on.
 *
 * @author woaitqs@gmail.com
 *         Created on 08/04/2018.
 */

public enum NierCamera {

  INSTANCE;

  private Camera cameraDevice;
  private CameraOption cameraOption;

  // cache cameraInfo if fetched, because it supposed to keep the same value.
  private Camera.CameraInfo[] cameraInfos;

  /**
   * open camera with given option.
   *
   * @param optionArgu option associated with camera.
   * @throws NotFoundException if camera not found.
   */
  public void open(@Nullable CameraOption optionArgu) throws NotFoundException {

    cameraOption = optionArgu != null ? optionArgu : CameraOption.createDefault();
    Logger.d("open camera with %s.", cameraOption.toString());

    fillUpCameraInfos();

    // release existing camera devices.
    release();

    // find right camera and try to open it.
    boolean findCamera = false;
    int orientation = 0;
    for (int i = 0; i < cameraInfos.length; i++) {
      boolean isFrontCamera = cameraInfos[i].facing == Camera.CameraInfo.CAMERA_FACING_FRONT;
      if (cameraOption.facingFront == isFrontCamera) {
        cameraDevice = Camera.open(i);
        orientation = cameraInfos[i].orientation;
        findCamera = true;
        break;
      }
    }

    if (!findCamera) {
      throw new NotFoundException(
          "Can't find camera devices which facing front argument is " + cameraOption.facingFront);
    }

    Camera.Parameters parameters = cameraDevice.getParameters();

    // set preview size parameters.
    Camera.Size size = adjustPreviewSize(
        cameraOption.previewWidth,
        cameraOption.previewHeight,
        orientation,
        cameraDevice.getParameters().getSupportedPreviewSizes());
    parameters.setPreviewSize(size.width, size.height);

    // set fps range parameters.
    List<int[]> ranges = parameters.getSupportedPreviewFpsRange();
    // fps value shoule be multiplied by 1000,
    // For example, if framerate is 26.623 frames per second, the value is 26623.
    int[] fpsRange = adjustFpsRange(cameraOption.fps * 1000, ranges);
    parameters.setPreviewFpsRange(fpsRange[0], fpsRange[1]);

    parameters.setFlashMode(cameraOption.flashMode);
    parameters.setFocusMode(cameraOption.autoFocus
        ? Camera.Parameters.FOCUS_MODE_AUTO
        : Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);

    // apply option parameters.
    cameraDevice.setParameters(parameters);
  }

  // Sets the {@link SurfaceTexture} to be used for live preview.
  // Either a surface or surface texture is necessary for preview, and
  // preview is necessary to take pictures.  The same surface texture can be
  // re-set without harm.  Setting a preview surface texture will un-set any
  // preview surface that was set via {@link #setPreviewDisplay}.
  public void setPreviewTexture(SurfaceTexture surfaceTexture) throws IOException {
    if (cameraDevice != null) {
      cameraDevice.setPreviewTexture(surfaceTexture);
    }
  }

  public void startPreview() {
    if (cameraDevice != null) {
      cameraDevice.startPreview();
    }
  }

  public void stopPreview() {
    if (cameraDevice == null) {
      return;
    }
    // Stops capturing and drawing preview frames to the surface, and
    // resets the camera for a future call to {@link #startPreview()}.
    cameraDevice.stopPreview();
  }

  /**
   *
   */
  public void release() {
    if (cameraDevice == null) {
      return;
    }
    // Disconnects and releases the Camera object resources.
    cameraDevice.release();
    cameraDevice = null;
  }

  private void fillUpCameraInfos() {
    if (cameraInfos != null) {
      return;
    }
    int numOfCameras = Camera.getNumberOfCameras();
    cameraInfos = new Camera.CameraInfo[numOfCameras];
    for (int i = 0; i < numOfCameras; i++) {
      Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
      Camera.getCameraInfo(i, cameraInfo);
      cameraInfos[i] = cameraInfo;
    }
  }

  /**
   * there may be many kinds of preview size, choose acreage the most fit one.
   */
  private static Camera.Size adjustPreviewSize(
      int expectWidth, int expectHeight, int orientation, List<Camera.Size> supportSize) {

    int checkWidth = Utils.isLandScape(orientation) ? expectHeight : expectWidth;
    int checkHeight = Utils.isLandScape(orientation) ? expectWidth : expectHeight;

    int minDiff = Integer.MAX_VALUE;
    int chooseIndex = 0;
    for (int i = 0; i < supportSize.size(); i++) {
      int currentDiff =
          Math.abs(supportSize.get(i).width * supportSize.get(i).height - checkWidth * checkHeight);
      if (currentDiff < minDiff) {
        minDiff = currentDiff;
        chooseIndex = i;
      }
    }

    Logger.d("finally set preview size to (%d X %d).",
        supportSize.get(chooseIndex).width, supportSize.get(chooseIndex).height);

    return supportSize.get(chooseIndex);
  }

  private static int[] adjustFpsRange(int expectRange, List<int[]> ranges) {

    int selectIndex = C.NOT_SET;
    int minDiff = Integer.MAX_VALUE;
    if (ranges != null) {
      for (int i = 0; i < ranges.size(); i++) {
        int minFps = ranges.get(i)[PREVIEW_FPS_MIN_INDEX];
        int maxFps = ranges.get(i)[PREVIEW_FPS_MAX_INDEX];
        if (expectRange < minFps || expectRange > maxFps) {
          continue;
        }
        int currentDiff = Math.abs(expectRange - minFps) + Math.abs(expectRange - maxFps);
        if (currentDiff < minDiff) {
          minDiff = currentDiff;
          selectIndex = i;
        }
      }
    }

    return selectIndex == C.NOT_SET
        ? new int[]{C.DEFAULT_FPS, C.DEFAULT_FPS}
        : ranges.get(selectIndex);
  }

  public static class NotFoundException extends Exception {
    NotFoundException(String message) {
      super(message);
    }
  }

}

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

package com.qisen.android.nier.record;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.support.annotation.NonNull;

import com.qisen.android.nier.opengl.ScreenDrawer;
import com.qisen.android.nier.record.camera.CameraOption;
import com.qisen.android.nier.record.camera.NierCamera;
import com.qisen.android.nier.util.Logger;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * facade to composite {@link com.qisen.android.nier.record.camera.NierCamera}, and
 * {@link NierRecordSurfaceTexture} and {@link GLSurfaceView},
 * provide an implementation of {@link IRecorder}
 *
 * @author woaitqs@gmail.com
 *         Created on 09/04/2018.
 */

public class NierRecorder implements IRecorder {

  private int textureId;

  private NierRecordSurfaceTexture surfaceTexture;
  private GLSurfaceView glSurfaceView;
  private CameraOption cameraOption;

  public NierRecorder(CameraOption cameraOption) {
    this.cameraOption = cameraOption;
  }

  private GLSurfaceView.Renderer glSurfaceViewRenderer = new GLSurfaceView.Renderer() {

    private float[] transformMatrix = new float[16];
    private ScreenDrawer screenDrawer;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
      // This method will be called when the surface is created or recreated.
      // eglGetDisplay -> eglInitialize -> eglChooseConfig -> eglCreateContext
      // createWindowSurface
      // makeCurrent function to bind eglSurface and context
      // so that, content can be displayed.

      // create surface texture.
      surfaceTexture = NierRecordSurfaceTexture.newInstance();
      textureId = surfaceTexture.getTextureId();
      screenDrawer = new ScreenDrawer(textureId);

      // add surface texture frame listener.
      // If new image data came out, onFrameAvailable will be called.
      surfaceTexture.addFrameListener(new NierRecordSurfaceTexture.FrameListener() {
        @Override
        public void onFrameAvailable() {
          if (glSurfaceView != null) {
            glSurfaceView.requestRender();
          }
        }
      });

      // set default background(black).
      GLES20.glClearColor(0F, 0F, 0F, 1F);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
      // open camera
      try {
        NierCamera.INSTANCE.open(cameraOption);
      } catch (NierCamera.NotFoundException e) {
        Logger.e("Can't open camera with given CameraOption.");
        e.printStackTrace();
      }
      try {
        NierCamera.INSTANCE.setPreviewTexture(surfaceTexture);
      } catch (IOException e) {
        Logger.e("Failed to set preview texture.");
        e.printStackTrace();
      }
      // start preview.
      NierCamera.INSTANCE.startPreview();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
      // 更新纹理图像
      surfaceTexture.updateTexImage();
      // 获取外部纹理的矩阵，用来确定纹理的采样位置，没有此矩阵可能导致图像翻转等问题
      surfaceTexture.getTransformMatrix(transformMatrix);
      screenDrawer.draw(transformMatrix);
    }
  };

  @Override
  public void attachGLSurfaceView(
      @NonNull final GLSurfaceView glSurfaceView) {

    this.glSurfaceView = glSurfaceView;

    // GLThread will start only when after calling `setRenderer` function.
    glSurfaceView.setEGLContextClientVersion(2);
    glSurfaceView.setRenderer(glSurfaceViewRenderer);
    glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

  }

  @Override
  public void startRecord() {

    // must be used after attachGLSurfaceView.

  }

  @Override
  public void stopRecord() {

  }

  /**
   * release SurfaceTexture created.
   */
  public void release() {
    if (surfaceTexture != null) {
      surfaceTexture.release();
    }
    NierCamera.INSTANCE.release();
  }

}
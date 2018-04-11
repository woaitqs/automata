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

import android.hardware.Camera;
import android.support.annotation.FloatRange;
import android.support.annotation.StringDef;

import com.qisen.android.nier.C;
import com.qisen.android.nier.annotation.TODO;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Some option used in {@link NierCamera} to determine how to use camera devices.
 *
 * @author woaitqs@gmail.com
 *         Created on 08/04/2018.
 */

public class CameraOption {


  @Retention(RetentionPolicy.SOURCE)
  @StringDef({Camera.Parameters.FLASH_MODE_AUTO, Camera.Parameters.FLASH_MODE_OFF,
      Camera.Parameters.FLASH_MODE_ON,
      Camera.Parameters.FLASH_MODE_RED_EYE, Camera.Parameters.FLASH_MODE_TORCH})
  public @interface FLASH_MODE { }

  boolean facingFront;
  String flashMode;
  float speedMultiplier;
  @TODO(detail = "support multi focus mode.")
  boolean autoFocus;
  int fps;
  int previewWidth;
  int previewHeight;

  private CameraOption(Builder builder) {
    facingFront = builder.facingFront;
    flashMode = builder.flashMode;
    speedMultiplier = builder.speedMultiplier;
    autoFocus = builder.autoFocus;
    fps = builder.fps;
    previewWidth = builder.previewWidth;
    previewHeight = builder.previewHeight;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return "CameraOption{" +
        "facingFront=" + facingFront +
        ", flashMode='" + flashMode + '\'' +
        ", speedMultiplier=" + speedMultiplier +
        ", autoFocus=" + autoFocus +
        ", fps=" + fps +
        ", previewWidth=" + previewWidth +
        ", previewHeight=" + previewHeight +
        '}';
  }

  static CameraOption createDefault() {
    return newBuilder()
        .setFacingFront(true)
        .setFlashMode(Camera.Parameters.FLASH_MODE_OFF)
        .setSpeedMultiplier(C.NORMAL_SPEED_MULTIPLIER)
        .setAutoFocus(true)
        .setFps(C.DEFAULT_FPS)
        .build();
  }

  public static final class Builder {
    private boolean facingFront = false;
    private String flashMode = Camera.Parameters.FLASH_MODE_OFF;
    private float speedMultiplier = C.NORMAL_SPEED_MULTIPLIER;
    private boolean autoFocus = true;
    private int fps = C.DEFAULT_FPS;
    private int previewWidth = C.DEFAULT_PREVIEW_WIDTH;
    private int previewHeight = C.DEFAULT_PREVIEW_HEIGHT;

    private Builder() {
    }

    public Builder setFacingFront(boolean facingFront) {
      this.facingFront = facingFront;
      return this;
    }

    public Builder setFlashMode(@FLASH_MODE String flashMode) {
      this.flashMode = flashMode;
      return this;
    }

    /**
     * use float range to restrict speed multiplier value.
     *
     * @param speedMultiplier from 0.25F to 4F
     * @return builder self.
     */
    public Builder setSpeedMultiplier(@FloatRange(from = 0.25F, to = 4F) float speedMultiplier) {
      this.speedMultiplier = speedMultiplier;
      return this;
    }

    public Builder setAutoFocus(boolean autoFocus) {
      this.autoFocus = autoFocus;
      return this;
    }

    public Builder setFps(int fps) {
      this.fps = fps;
      return this;
    }

    public Builder setPreviewWidth(int previewWidth) {
      this.previewWidth = previewWidth;
      return this;
    }

    public Builder setPreviewHeight(int previewHeight) {
      this.previewHeight = previewHeight;
      return this;
    }

    public CameraOption build() {
      return new CameraOption(this);
    }
  }
}

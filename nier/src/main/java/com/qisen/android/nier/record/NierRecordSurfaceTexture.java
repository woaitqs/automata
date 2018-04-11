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

import android.graphics.SurfaceTexture;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;

import java.util.HashSet;
import java.util.Set;

/**
 * Receive image stream data from {@link android.hardware.Camera},
 * and notify {@link #frameListeners} when new frame available.
 *
 * normally, {@link #frameListeners} is :
 * 1. preview surfaceView.
 * 2. inputSurface.
 *
 * @author woaitqs@gmail.com
 *         Created on 09/04/2018.
 */
public class NierRecordSurfaceTexture extends SurfaceTexture {

  private int textureId;

  public interface FrameListener {
    void onFrameAvailable();
  }

  private NierRecordSurfaceTexture(int texName) {
    super(texName);
  }

  private Set<FrameListener> frameListeners = new HashSet<>();

  public static NierRecordSurfaceTexture newInstance() {

    // generate texture id.
    int[] textures = new int[1];
    GLES20.glGenTextures(1, textures, 0);
    int textureId = textures[0];

    // active texture and apply some settings.
    final NierRecordSurfaceTexture texture = new NierRecordSurfaceTexture(textureId);
    GLES20.glDisable(GLES20.GL_DEPTH_TEST);
    GLES20.glDisable(GLES20.GL_CULL_FACE);
    GLES20.glDisable(GLES20.GL_BLEND);
    GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
    // bind texture.
    GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, textureId);
    GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
        GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
    GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
        GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
    GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
        GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
    GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
        GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
    // unbind texture.
    GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, 0);

    texture.setOnFrameAvailableListener(new OnFrameAvailableListener() {
      @Override
      public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        if (texture.getFrameListeners() == null) {
          return;
        }
        for (FrameListener frameListener : texture.getFrameListeners()) {
            frameListener.onFrameAvailable();
        }
      }
    });

    texture.setTextureId(textureId);

    return texture;
  }

  Set<FrameListener> getFrameListeners() {
    return frameListeners;
  }

  public void addFrameListener(FrameListener frameListener) {
    frameListeners.add(frameListener);
  }

  public int getTextureId() {
    return textureId;
  }

  public void setTextureId(int textureId) {
    this.textureId = textureId;
  }

  @Override
  public void release() {
    frameListeners.clear();
    super.release();
  }
}

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

import android.opengl.GLSurfaceView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.qisen.android.nier.record.camera.CameraOption;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * interface which recorder should use.
 *
 * @author woaitqs@gmail.com
 *         Created on 09/04/2018.
 */

public interface IRecorder {

  /**
   * recorder need to attach a {@link GLSurfaceView}.
   * After calling this function, Nier will set {@link android.opengl.GLSurfaceView.Renderer},
   * so GLThread will start to do the work below.
   *    1. init egl
   *    2. create surface
   *    3. bind it, make it run
   *
   * {@link android.opengl.GLSurfaceView.Renderer#onSurfaceCreated(GL10, EGLConfig)} means that
   * eglSurface is able to run, and opengl command can be executed.
   *
   * <p>
   * NOTE: If user don't invoke this method, nothing will happen.
   * </p>
   *
   * @param glSurfaceView GL SurfaceView.
   */
  void attachGLSurfaceView(@NonNull GLSurfaceView glSurfaceView);

  void startRecord();

  void stopRecord();

}

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

package com.qisen.android.automata;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.qisen.android.nier.record.NierRecorder;

/**
 * @author woaitqs@gmail.com
 *         Created on 09/04/2018.
 */

public class PreviewActivity extends Activity {

  private NierRecorder recorder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.record_activity);
    testRecord();
  }

  private void testRecord() {
    GLSurfaceView glSurfaceView = findViewById(R.id.record_gsv);
    recorder = new NierRecorder(null);
    recorder.attachGLSurfaceView(glSurfaceView);
  }

  @Override
  protected void onPause() {
    super.onPause();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (recorder != null) {
      recorder.release();
    }
  }
}

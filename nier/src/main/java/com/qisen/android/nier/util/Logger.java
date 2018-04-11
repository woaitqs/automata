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

package com.qisen.android.nier.util;

import android.util.Log;

import com.qisen.android.nier.BuildConfig;

/**
 * @author qisen.tqs@alibaba-inc.com
 *         Created on 15/03/2018.
 */

public class Logger {

  private static final String TAG = "Nier";
  private static boolean isDebug = BuildConfig.DEBUG;

  public static void e(String formatMsg, Object... args) {
    if (isDebug) {
      Log.e(TAG,"**************** ERROR MESSAGE ***********************");
      Log.e(TAG, String.format(formatMsg, args));
      Log.e(TAG,"**************** ERROR MESSAGE ***********************");
    }
  }

  public static void e(String message) {
    if (isDebug) {
      Log.e(TAG,"**************** ERROR MESSAGE ***********************");
      Log.e(TAG, message);
      Log.e(TAG,"**************** ERROR MESSAGE ***********************");
    }
  }

  public static void d(String formatMsg, Object... args) {
    if (isDebug) {
      Log.d(TAG, String.format(formatMsg, args));
    }
  }

  public static void d(String message) {
    if (isDebug) {
      Log.d(TAG, message);
    }
  }

}

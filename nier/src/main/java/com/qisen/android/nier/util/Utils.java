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

import java.util.Collection;

/**
 * @author qisen.tqs@alibaba-inc.com
 *         Created on 15/03/2018.
 */

public class Utils {

  /**
   * @return whether a collection is empty.
   */
  public static boolean isEmpty(Collection collection) {
    return collection == null || collection.isEmpty();
  }

  /**
   * @return 传感器方向是否水平.
   */
  public static boolean isLandScape(int orientation) {
    return orientation % 270 == 0 || orientation % 90 == 0;
  }

}

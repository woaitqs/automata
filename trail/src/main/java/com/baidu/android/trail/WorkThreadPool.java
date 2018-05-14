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

package com.baidu.android.trail;

import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class WorkThreadPool {

  private static ExecutorService executor;

  private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
  private static final int KEEP_ALIVE_SECONDS = 30;

  private enum Priority {
    NORMAL,
    LOW
  }

  private WorkThreadPool() {}

  static {

    ThreadFactory workThreadFactory = new ThreadFactory() {
      private final AtomicInteger mCount = new AtomicInteger(1);

      public Thread newThread(@NonNull Runnable r) {
        return new Thread(r, "WorkThreadPool #" + mCount.getAndIncrement());
      }
    };

    executor = new ThreadPoolExecutor(
        CPU_COUNT * 2,
        CPU_COUNT * 4,
        KEEP_ALIVE_SECONDS,
        TimeUnit.SECONDS,
        new PriorityBlockingQueue<Runnable>(),
        workThreadFactory);
  }

  public static void execute(Runnable runnable) {
    execute(runnable, true);
  }

  public static void execute(Runnable runnable, boolean immediately) {
    executor.execute(new PriorityRunnable(immediately ? Priority.NORMAL : Priority.LOW, runnable));
  }

  private static class PriorityRunnable implements Runnable, Comparable<PriorityRunnable> {

    private final Priority priority;
    private final Runnable runnable;

    PriorityRunnable(Priority priority, Runnable runnable) {
      this.priority = priority;
      this.runnable = runnable;
    }

    @Override
    public void run() {
      if (this.runnable != null) {
        this.runnable.run();
      }
    }

    @Override
    public int compareTo(@NonNull PriorityRunnable runnable) {
      return priority.ordinal() - runnable.priority.ordinal();
    }
  }

}
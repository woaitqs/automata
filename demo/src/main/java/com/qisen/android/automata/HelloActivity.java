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
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author woaitqs@gmail.com
 *         Created on 11/04/2018.
 */

public class HelloActivity extends Activity {

  public static final String VETEXT_SHADER =
          " attribute vec4 vPosition;\n" +
          " void main() {\n" +
          "     gl_Position = vPosition;\n" +
          " }";

  public static final String FRAGMENT_SHADER =
          "precision mediump float;\n" +
          "uniform vec4 vColor;\n" +
          "void main() {\n" +
          "    gl_FragColor = vColor;\n" +
          "}";

  private static final int COORDS_PER_VERTEX = 3;
  private float triangleCoords[] = {
      0.5f, 0.5f, 0.0f, // top
      -0.5f, -0.5f, 0.0f, // bottom left
      0.5f, -0.5f, 0.0f  // bottom right
  };

  private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
  private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

  private float color[] = {1.0f, 1.0f, 1.0f, 1.0f};

  private FloatBuffer vertexBuffer;

  private int mProgram;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.hello_activity);
    GLSurfaceView glSurfaceView = findViewById(R.id.hello_gsv);
    // Create an OpenGL ES 2.0 context.
    glSurfaceView.setEGLContextClientVersion(2);
    glSurfaceView.setRenderer(new GLSurfaceView.Renderer() {
      @Override
      public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0F, 0F, 0F, 1F);
        //申请底层空间
        ByteBuffer bb = ByteBuffer.allocateDirect(
            triangleCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        //将坐标数据转换为FloatBuffer，用以传入给OpenGL ES程序
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,
            VETEXT_SHADER);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,
            FRAGMENT_SHADER);

        //创建一个空的OpenGLES程序
        mProgram = GLES20.glCreateProgram();
        //将顶点着色器加入到程序
        GLES20.glAttachShader(mProgram, vertexShader);
        //将片元着色器加入到程序中
        GLES20.glAttachShader(mProgram, fragmentShader);
        //连接到着色器程序
        GLES20.glLinkProgram(mProgram);
      }

      @Override
      public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
      }

      @Override
      public void onDrawFrame(GL10 gl) {
        //将程序加入到OpenGLES2.0环境
        GLES20.glUseProgram(mProgram);

        //获取顶点着色器的vPosition成员句柄
        int mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //准备三角形的坐标数据
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
            GLES20.GL_FLOAT, false,
            vertexStride, vertexBuffer);
        //获取片元着色器的vColor成员的句柄
        int mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        //设置绘制三角形的颜色
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
        //绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        //禁止顶点数组的句柄
        GLES20.glDisableVertexAttribArray(mPositionHandle);
      }
    });
    glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
  }

  /**
   * Utility method for compiling a OpenGL shader.
   * <p>
   * <p><strong>Note:</strong> When developing shaders, use the checkGlError()
   * method to debug shader coding errors.</p>
   *
   * @param type       - Vertex or fragment shader type.
   * @param shaderCode - String containing the shader code.
   * @return - Returns an id for the shader.
   */
  public static int loadShader(int type, String shaderCode) {

    // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
    // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
    int shader = GLES20.glCreateShader(type);

    // add the source code to the shader and compile it
    GLES20.glShaderSource(shader, shaderCode);
    GLES20.glCompileShader(shader);

    return shader;
  }
}

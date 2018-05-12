package com.baidu.android.trail.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.android.trail.R;

public class SplashActivity extends AppCompatActivity {

  private Handler handler;
  private Runnable runnable;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    initData();
  }

  private void initData() {
    //三秒钟跳转到主界面
    handler = new Handler();
    runnable = new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(SplashActivity.this,MainViewActivity.class);
        startActivity(intent);
        finish();
      }
    };
   handler.postDelayed(runnable,2000);
  }

  @Override
  protected void onStop() {
    super.onStop();
    handler.removeCallbacks(runnable);
  }
}

package com.qisen.android.automata;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 第一次启动 引导界面
 */
public class SplashActivity extends AppCompatActivity {
  private ViewPager viewPager;

  public static boolean isFristLoad = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_splash);
      initView();
      initData();
    }

  private void initData() {

  }

  private void initView() {
    viewPager = findViewById(R.id.vp_load);
  }
}

package com.baidu.android.trail;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * @author qisen (tangqisen@wandoujia.com)
 */

public class TrailApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Bmob.initialize(this, "65b91c220262f63939f59aaa147622ae");
  }

}

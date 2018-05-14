package com.baidu.android.trail;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.baidu.android.trail.db.SubjectDB;

import cn.bmob.v3.Bmob;

public class TrailApp extends Application {

  private static SubjectDB subjectDB;

  @Override
  public void onCreate() {
    super.onCreate();
    Bmob.initialize(this, "65b91c220262f63939f59aaa147622ae");

    subjectDB =
        Room.databaseBuilder(this, SubjectDB.class, "subject_db").build();
  }

  public static SubjectDB getSubjectDB() {
    return subjectDB;
  }
}

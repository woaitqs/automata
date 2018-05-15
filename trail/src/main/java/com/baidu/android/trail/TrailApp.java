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
    Bmob.initialize(this, "bc00ae86b74fa4fdb0abf5e014a8ffce");

    subjectDB =
        Room.databaseBuilder(this, SubjectDB.class, "subject_db").build();
  }

  public static SubjectDB getSubjectDB() {
    return subjectDB;
  }
}

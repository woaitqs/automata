package com.baidu.android.trail.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {SubjectEntity.class}, version = 1)
public abstract class SubjectDB extends RoomDatabase {

  public abstract SubjectDAO subjectDAO();

}

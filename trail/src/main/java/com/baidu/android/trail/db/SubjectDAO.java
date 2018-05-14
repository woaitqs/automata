package com.baidu.android.trail.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface SubjectDAO {

  @Query("SELECT * FROM subject where type = 1")
  public SubjectEntity[] loadFavSubjects();

  @Query("SELECT * FROM subject where type = 2")
  public SubjectEntity[] loadErrorSubjects();

  @Delete
  public void deleteSubject(SubjectEntity... entities);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public void insertUsers(SubjectEntity... entities);

}

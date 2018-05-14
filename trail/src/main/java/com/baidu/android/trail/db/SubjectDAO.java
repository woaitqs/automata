package com.baidu.android.trail.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.baidu.android.trail.bean.Subject;

@Dao
public interface SubjectDAO {

  @Query("SELECT * FROM subject where type = 1")
  public SubjectEntity[] loadFavSubjects();

  @Query("SELECT * FROM subject where type = 2")
  public SubjectEntity[] loadErrorSubjects();

  @Query("SELECT * FROM subject where type = :type and question = :question")
  public SubjectEntity[] getSubject(String question, int type);

  @Delete
  public void deleteSubject(SubjectEntity... entities);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public void insertSubject(SubjectEntity... entities);

}

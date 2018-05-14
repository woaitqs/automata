package com.baidu.android.trail.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(indices = {@Index("id")}, tableName = "subject")
public class SubjectEntity {

  @PrimaryKey(autoGenerate = true)
  public int id;

  public String question;

  public String picture;

  // type for favorite or error.
  // 1 for fav
  // 2 for error.
  public int type;

  public int answer;

  @ColumnInfo(name = "option_a")
  public String optionA;
  @ColumnInfo(name = "option_b")
  public String optionB;
  @ColumnInfo(name = "option_c")
  public String optionC;
  @ColumnInfo(name = "option_d")
  public String optionD;

}

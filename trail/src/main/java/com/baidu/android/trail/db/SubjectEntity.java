package com.baidu.android.trail.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.baidu.android.trail.bean.Subject;

@Entity(primaryKeys = {"question", "type"}, indices = {@Index("question")}, tableName = "subject")
public class SubjectEntity {

  @NonNull
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

  public static SubjectEntity from(int type, Subject subject) {
    SubjectEntity subjectEntity = new SubjectEntity();
    subjectEntity.question = subject.getQuestion();
    subjectEntity.picture = subject.getPicture();
    subjectEntity.answer = subject.getAnswer();
    subjectEntity.optionA = subject.getOptionA();
    subjectEntity.optionB = subject.getOptionB();
    subjectEntity.optionC = subject.getOptionC();
    subjectEntity.optionD = subject.getOptionD();
    subjectEntity.type = type;
    return subjectEntity;
  }

}

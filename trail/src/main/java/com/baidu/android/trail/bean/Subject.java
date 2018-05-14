package com.baidu.android.trail.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class Subject extends BmobObject implements Serializable {

  private static final long serialVersionUID = -4688817163385967887L;

  private String question;

  // 配图
  private String picture;

  private int answer;

  private String optionA;
  private String optionB;
  private String optionC;
  private String optionD;

  public void setQuestion(String question) {
    this.question = question;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public void setAnswer(int answer) {
    this.answer = answer;
  }

  public void setOptionA(String optionA) {
    this.optionA = optionA;
  }

  public void setOptionB(String optionB) {
    this.optionB = optionB;
  }

  public void setOptionC(String optionC) {
    this.optionC = optionC;
  }

  public void setOptionD(String optionD) {
    this.optionD = optionD;
  }

  public String getQuestion() {
    return question;
  }

  public String getPicture() {
    return picture;
  }

  public int getAnswer() {
    return answer;
  }

  public String getOptionA() {
    return optionA;
  }

  public String getOptionB() {
    return optionB;
  }

  public String getOptionC() {
    return optionC;
  }

  public String getOptionD() {
    return optionD;
  }
}

package com.baidu.android.trail.bean;

import cn.bmob.v3.BmobObject;

/**
 * @author qisen (tangqisen@wandoujia.com)
 */

public class Subject extends BmobObject {

  private String question;
  private int choiceNum;
  private int type;
  private String choiceA;
  private String choiceB;
  private String choiceC;
  private String choiceD;

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public int getChoiceNum() {
    return choiceNum;
  }

  public void setChoiceNum(int choiceNum) {
    this.choiceNum = choiceNum;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getChoiceA() {
    return choiceA;
  }

  public void setChoiceA(String choiceA) {
    this.choiceA = choiceA;
  }

  public String getChoiceB() {
    return choiceB;
  }

  public void setChoiceB(String choiceB) {
    this.choiceB = choiceB;
  }

  public String getChoiceC() {
    return choiceC;
  }

  public void setChoiceC(String choiceC) {
    this.choiceC = choiceC;
  }

  public String getChoiceD() {
    return choiceD;
  }

  public void setChoiceD(String choiceD) {
    this.choiceD = choiceD;
  }
}

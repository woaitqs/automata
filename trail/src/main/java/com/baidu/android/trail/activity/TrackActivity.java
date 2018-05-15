package com.baidu.android.trail.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.baidu.android.trail.R;
import com.baidu.android.trail.TrailApp;
import com.baidu.android.trail.WorkThreadPool;
import com.baidu.android.trail.bean.Subject;
import com.baidu.android.trail.db.SubjectEntity;
import com.baidu.android.trail.fragment.QuestionFragment;
import com.baidu.android.trail.fragment.ResultFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TrackActivity extends AppCompatActivity {

  private List<Subject> subjects;

  private TextView wrongText;
  private TextView correctText;
  private TextView totalText;
  private CheckBox favCheckBox;

  private int totalNum;
  private int correctNum;
  private int wrongNum;
  private int progressNum;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_track);

    totalText = findViewById(R.id.item_total);
    correctText = findViewById(R.id.item_correct);
    wrongText = findViewById(R.id.item_wrong);
    favCheckBox = findViewById(R.id.item_favorite);

    subjects = (List<Subject>) getIntent().getSerializableExtra("data");
    totalNum = subjects.size();
    wrongNum = 0;
    correctNum = 0;
    progressNum = 1;
    displayQuestion(progressNum - 1);

    favCheckBox.setOnCheckedChangeListener(
        new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
            WorkThreadPool.execute(new Runnable() {
              @Override
              public void run() {
                if (isChecked) {
                  TrailApp.getSubjectDB().subjectDAO().insertSubject(
                      SubjectEntity.from(1, subjects.get(wrongNum + correctNum)));
                } else {
                  TrailApp.getSubjectDB().subjectDAO().deleteSubject(
                      SubjectEntity.from(1, subjects.get(wrongNum + correctNum)));
                }
              }
            }, true);
          }
        }
    );
  }

  public static void launch(Context context, List<Subject> subjects) {
    Intent intent = new Intent(context, TrackActivity.class);
    intent.putExtra("data", (Serializable) subjects);
    context.startActivity(intent);
  }

  private void displayQuestion(final int position) {
    QuestionFragment questionFragment =
        QuestionFragment.newInstance(
            subjects.get(position), position == subjects.size() - 1, false);
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.fragment_container, questionFragment)
        .commit();
    questionFragment.setConfirmListener(new QuestionFragment.ConfirmListener() {
      @Override
      public void onConfirm(final Subject currentSubject, int selectValue) {
        if (selectValue == currentSubject.getAnswer()) {
          correctNum++;
        } else {
          WorkThreadPool.execute(new Runnable() {
            @Override
            public void run() {
              TrailApp.getSubjectDB().subjectDAO().insertSubject(
                  SubjectEntity.from(2, currentSubject));
            }
          }, true);
          wrongNum++;
        }
        progressNum++;
        boolean finish = position == subjects.size() - 1;
        if (finish) {
          getSupportFragmentManager()
              .beginTransaction()
              .replace(R.id.fragment_container,
                  ResultFragment.newInstance(correctNum, wrongNum, totalNum))
              .commit();
          favCheckBox.setVisibility(View.GONE);
        } else {
          displayQuestion(position + 1);
        }
      }
    });
    totalText.setText(progressNum + "/" + totalNum);
    wrongText.setText(wrongNum + "");
    correctText.setText(correctNum + "");
    WorkThreadPool.execute(new Runnable() {
      @Override
      public void run() {
        SubjectEntity[] entities =
            TrailApp.getSubjectDB().subjectDAO().getSubject(subjects.get(position).getQuestion(), 1);
        final boolean hasFav = entities != null && entities.length > 0;
        favCheckBox.post(new Runnable() {
          @Override
          public void run() {
            favCheckBox.setChecked(hasFav);
          }
        });
      }
    });
  }

  public static void mock(Context context) {
    List<Subject> subjects = new ArrayList<>();

    Subject subject1 = new Subject();
    subject1.setQuestion("Question is what ? 1");
    subject1.setOptionA("A: setOptionAsetOptionA");
    subject1.setOptionB("B: setOptionA");
    subject1.setOptionC("C: setOptionAsetOptionA");
    subject1.setOptionD("D: setOptionAsetOptionAsetOptionA");
    subject1.setAnswer(1);
    subjects.add(subject1);

    Subject subject2 = new Subject();
    subject2.setQuestion("Question is what ? 2");
    subject2.setOptionA("A: setOptionAsetOptionA");
    subject2.setOptionB("B: setOptionA");
    subject2.setOptionC("C: setOptionAsetOptionA");
    subject2.setOptionD("D: setOptionAsetOptionAsetOptionA");
    subject2.setAnswer(1);
    subjects.add(subject2);

    Subject subject3 = new Subject();
    subject3.setQuestion("Question is what ? 3");
    subject3.setOptionA("A: setOptionAsetOptionA");
    subject3.setOptionB("B: setOptionA");
    subject3.setOptionC("C: setOptionAsetOptionA");
    subject3.setOptionD("D: setOptionAsetOptionAsetOptionA");
    subject3.setAnswer(1);
    subjects.add(subject3);

    launch(context, subjects);
  }

}

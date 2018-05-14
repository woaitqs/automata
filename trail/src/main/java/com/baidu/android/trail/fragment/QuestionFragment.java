package com.baidu.android.trail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.android.trail.R;
import com.baidu.android.trail.bean.Subject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class QuestionFragment extends Fragment {

  private TextView question;
  private ImageView picture;
  private TextView optionA;
  private TextView optionB;
  private TextView optionC;
  private TextView optionD;

  private Subject subject;

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    question = view.findViewById(R.id.question);
    picture = view.findViewById(R.id.picture);
    optionA = view.findViewById(R.id.optionA);
    optionB = view.findViewById(R.id.optionB);
    optionC = view.findViewById(R.id.optionC);
    optionD = view.findViewById(R.id.optionD);

    subject = new Subject();
    subject.setQuestion("Question is what ?");
    subject.setOptionA("A: setOptionAsetOptionA");
    subject.setOptionB("B: setOptionA");
    subject.setOptionC("C: setOptionAsetOptionA");
    subject.setOptionD("D: setOptionAsetOptionAsetOptionA");
    subject.setAnswer(1);
    applySubject(subject);
  }

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return LayoutInflater.from(getContext())
        .inflate(R.layout.fragment_question_item, container, false);
  }

  private void applySubject(Subject subject) {
    question.setText(subject.getQuestion());
    boolean hasPicture = TextUtils.isEmpty(subject.getPicture());
    picture.setVisibility(hasPicture ? View.VISIBLE : View.GONE);
    if (hasPicture) {
      RequestOptions requestOptions =
          new RequestOptions().centerCrop().placeholder(R.color.image_default);
      Glide.with(this).load(subject.getPicture()).apply(requestOptions).into(picture);
    }
    optionA.setText(subject.getOptionA());
    optionB.setText(subject.getOptionB());
    if (!TextUtils.isEmpty(subject.getOptionC())) {
      optionC.setText(subject.getOptionC());
    }
    if (!TextUtils.isEmpty(subject.getOptionD())) {
      optionD.setText(subject.getOptionD());
    }
  }
}

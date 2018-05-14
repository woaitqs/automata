package com.baidu.android.trail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.android.trail.R;
import com.baidu.android.trail.bean.Subject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class QuestionFragment extends Fragment {

  private static final int NOT_SET = -1;

  private TextView question;
  private ImageView picture;
  private CheckBox optionA;
  private CheckBox optionB;
  private CheckBox optionC;
  private CheckBox optionD;

  private Button confirm;

  private boolean isLastOne;
  private Subject subject;

  private ConfirmListener confirmListener;

  public void setConfirmListener(ConfirmListener confirmListener) {
    this.confirmListener = confirmListener;
  }

  public interface ConfirmListener {
    void onConfirm(Subject subject, int selectValue);
  }

  public static QuestionFragment newInstance(Subject subject, boolean isLastOne) {
    QuestionFragment fragment = new QuestionFragment();
    Bundle bundle = new Bundle();
    bundle.putBoolean("last", isLastOne);
    bundle.putSerializable("subject", subject);
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    question = view.findViewById(R.id.question);
    picture = view.findViewById(R.id.picture);
    optionA = view.findViewById(R.id.optionA);
    optionB = view.findViewById(R.id.optionB);
    optionC = view.findViewById(R.id.optionC);
    optionD = view.findViewById(R.id.optionD);
    confirm = view.findViewById(R.id.confirm);

    subject = (Subject) getArguments().getSerializable("subject");
    isLastOne = getArguments().getBoolean("last");
    initViewWithSubject(subject);
  }

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return LayoutInflater.from(getContext())
        .inflate(R.layout.fragment_question_item, container, false);
  }

  private void initViewWithSubject(final Subject subject) {
    question.setText(subject.getQuestion());
    boolean hasPicture = !TextUtils.isEmpty(subject.getPicture());
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
    optionA.setOnCheckedChangeListener(changeListener);
    optionB.setOnCheckedChangeListener(changeListener);
    optionC.setOnCheckedChangeListener(changeListener);
    optionD.setOnCheckedChangeListener(changeListener);
    confirm.setText(isLastOne ? "提交本次测试" : "下一题");
    confirm.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        int select = getAnswer();
        if (getAnswer() == NOT_SET) {
          Toast.makeText(v.getContext(), "请选择一个选项", Toast.LENGTH_SHORT).show();
          return;
        }

        if (confirmListener != null) {
          confirmListener.onConfirm(subject, select);
        }
      }
    });
  }

  private CompoundButton.OnCheckedChangeListener changeListener = new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
      optionA.setChecked(false);
      optionB.setChecked(false);
      optionC.setChecked(false);
      optionD.setChecked(false);
      if (isChecked) {
        buttonView.setChecked(true);
      }
    }
  };

  private int getAnswer() {
    if (optionA.isChecked()) {
      return 1;
    } else if (optionB.isChecked()) {
      return 2;
    } else if (optionC.isChecked()) {
      return 3;
    } else if (optionD.isChecked()) {
      return 4;
    }
    return NOT_SET;
  }

}

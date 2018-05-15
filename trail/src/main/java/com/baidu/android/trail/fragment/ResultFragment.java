package com.baidu.android.trail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.android.trail.R;

public class ResultFragment extends Fragment {

  public static ResultFragment newInstance(int correct, int wrong, int total) {
    Bundle bundle = new Bundle();
    bundle.putInt("correct", correct);
    bundle.putInt("wrong", wrong);
    bundle.putInt("total", total);
    ResultFragment resultFragment = new ResultFragment();
    resultFragment.setArguments(bundle);
    return resultFragment;
  }

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return LayoutInflater.from(getContext())
        .inflate(R.layout.fragment_result, container, false);
  }

  @Override
  public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    TextView detail = view.findViewById(R.id.result_detail);

    int wrong = getArguments().getInt("wrong");
    int correct = getArguments().getInt("correct");
    int total = getArguments().getInt("total");

    String percent = (correct * 100 / total) + "%";

    detail.setText(getString(R.string.test_result, correct, wrong, percent));

  }
}

package com.baidu.android.trail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.android.trail.R;
import com.baidu.android.trail.activity.TrackActivity;

public class DisplayFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return LayoutInflater.from(getContext())
        .inflate(R.layout.fragment_display, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    view.findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        TrackActivity.mock(v.getContext());
      }
    });
  }
}

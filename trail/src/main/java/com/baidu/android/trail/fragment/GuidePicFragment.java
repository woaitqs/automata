package com.baidu.android.trail.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.baidu.android.trail.R;
import com.baidu.android.trail.activity.LoginActivity;
import com.baidu.android.trail.activity.MainViewActivity;

public class GuidePicFragment extends Fragment {

  private static final String KEY_IMAGE = "KEY_IMAGE";

  public static GuidePicFragment newInstance(int resId, boolean finish) {
    GuidePicFragment guidePicFragment = new GuidePicFragment();
    Bundle args = new Bundle();
    args.putInt(KEY_IMAGE, resId);
    args.putBoolean("finish", finish);
    guidePicFragment.setArguments(args);
    return guidePicFragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    return LayoutInflater.from(getContext())
        .inflate(R.layout.fragment_guide, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ImageView imageView = view.findViewById(R.id.guide_pic);
    ViewGroup.LayoutParams params = imageView.getLayoutParams();
    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
    params.height = ViewGroup.LayoutParams.MATCH_PARENT;
    imageView.setLayoutParams(params);

    int imgRes = getArguments().getInt(KEY_IMAGE);
    imageView.setImageResource(imgRes);
    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

    boolean finish = getArguments().getBoolean("finish");

    Button finishBtn = view.findViewById(R.id.guide_finish);
    finishBtn.setVisibility(finish ? View.VISIBLE : View.GONE);
    finishBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(v.getContext(), LoginActivity.class));
      }
    });
  }
}

package com.baidu.android.trail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class GuidePicFragment extends Fragment {

  private static final String KEY_IMAGE = "KEY_IMAGE";

  public static GuidePicFragment newInstance(int resId) {
    GuidePicFragment guidePicFragment = new GuidePicFragment();
    Bundle args = new Bundle();
    args.putInt(KEY_IMAGE, resId);
    guidePicFragment.setArguments(args);
    return guidePicFragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    return new ImageView(getContext());
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ImageView imageView = (ImageView) view;
    ViewGroup.LayoutParams params = imageView.getLayoutParams();
    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
    params.height = ViewGroup.LayoutParams.MATCH_PARENT;
    imageView.setLayoutParams(params);

    int imgRes = getArguments().getInt(KEY_IMAGE);
    imageView.setImageResource(imgRes);
    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
  }
}

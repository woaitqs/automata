package com.baidu.android.trail.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.android.trail.R;
import com.baidu.android.trail.activity.TrackActivity;
import com.baidu.android.trail.bean.Subject;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class DisplayFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return LayoutInflater.from(getContext())
        .inflate(R.layout.fragment_display, container, false);
  }

  @Override
  public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    view.findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        final ProgressDialog dialog = new ProgressDialog(view.getContext());
        dialog.setTitle("正在从网上拉取数据");
        dialog.show();

        BmobQuery<Subject> query = new BmobQuery<>();
        query.setLimit(20).findObjects(new FindListener<Subject>() {
          @Override
          public void done(List<Subject> list, BmobException e) {
            dialog.dismiss();
            if (e != null) {
              e.printStackTrace();
              Toast.makeText(view.getContext(), "拉取数据失败", Toast.LENGTH_LONG).show();
            } else {
              TrackActivity.launch(view.getContext(), list);
            }
          }
        });
      }
    });
  }
}

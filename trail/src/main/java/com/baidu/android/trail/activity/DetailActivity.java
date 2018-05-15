package com.baidu.android.trail.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.baidu.android.trail.R;
import com.baidu.android.trail.bean.Subject;
import com.baidu.android.trail.fragment.QuestionFragment;

public class DetailActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    Subject subject = (Subject) getIntent().getSerializableExtra("subject");
    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
        QuestionFragment.newInstance(subject, false, true)).commit();
  }

  public static void launch(Context context, Subject subject) {
    Intent intent = new Intent(context, DetailActivity.class);
    intent.putExtra("subject", subject);
    context.startActivity(intent);
  }
}

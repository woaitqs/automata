package com.baidu.android.trail.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.baidu.android.trail.R;
import com.baidu.android.trail.fragment.GuidePicFragment;
import com.baidu.android.trail.widget.FPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_guide);
    ViewPager pager = findViewById(R.id.viewpager);
    pager.setAdapter(new PicFragmentAdapter(getSupportFragmentManager()));

    FPageIndicator indicator = findViewById(R.id.indicator);
    indicator.attachToViewPager(pager);
  }

  private class PicFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Integer> guideDrawbles = new ArrayList<>();

    public PicFragmentAdapter(FragmentManager fm) {
      super(fm);
      guideDrawbles.add(R.drawable.yd1);
      guideDrawbles.add(R.drawable.yd2);
      guideDrawbles.add(R.drawable.yd3);
    }

    @Override
    public Fragment getItem(int position) {
      return GuidePicFragment.newInstance(guideDrawbles.get(position),
          position == guideDrawbles.size() - 1);
    }

    @Override
    public int getCount() {
      return guideDrawbles.size();
    }
  }
}

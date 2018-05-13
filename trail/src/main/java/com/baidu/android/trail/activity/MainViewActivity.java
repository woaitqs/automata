package com.baidu.android.trail.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.android.trail.R;
import com.baidu.android.trail.bean.Subject;
import com.baidu.android.trail.function.FunctionDrawable;
import com.baidu.android.trail.function.communication.CommunicationFragment;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainViewActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private FunctionDrawable activeDrawable = FunctionDrawable.COMMUNICATION;
  private FrameLayout fragmentContainer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_view);

    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    fragmentContainer = findViewById(R.id.fragment_container);

    initFunction();
    activeFunction(activeDrawable);
  }

  private void initFunction() {
    for (final FunctionDrawable drawable : FunctionDrawable.values()) {
      ImageView imageView = findViewById(drawable.getIconId());
      imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if (drawable.equals(activeDrawable)) {
            return;
          }
          activeFunction(drawable);
          activeDrawable = drawable;
        }
      });
    }
  }

  private void activeFunction(FunctionDrawable functionDrawable) {
    for (FunctionDrawable drawable : FunctionDrawable.values()) {
      ImageView imageView = findViewById(drawable.getIconId());
      boolean isSelect = drawable.equals(functionDrawable);
      imageView.setImageResource(isSelect
          ? drawable.getActiveResId()
          : drawable.getDefaultResId());
      if (isSelect) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, new CommunicationFragment())
            .commit();
      }
    }
  }

  private void test() {
    Subject subject = new Subject();
    subject.setQuestion("鹦鹉有几个嘴？");
    subject.setChoiceNum(4);
    subject.setType(1);
    subject.setChoiceA("1");
    subject.setChoiceB("2");
    subject.setChoiceC("3");
    subject.setChoiceD("4");

    subject.save(new SaveListener<String>() {
      @Override
      public void done(String objectId, BmobException e) {
        if (e == null) {
          Toast.makeText(MainViewActivity.this, "success", Toast.LENGTH_LONG).show();
        } else {
          Toast.makeText(MainViewActivity.this, "failed " + e.toString(), Toast.LENGTH_LONG).show();
        }
      }
    });

    BmobQuery<Subject> query = new BmobQuery<>();

  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main_view, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_personal) {//我的信息
    } else if (id == R.id.nav_record) {//测试记录

    } else if (id == R.id.nav_version) {//版本信息

    } else if (id == R.id.nav_feedback) {//反馈

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}

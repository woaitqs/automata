package com.baidu.android.trail.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.android.trail.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
  private EditText username;
  private EditText et_password;
  private Button btn_login;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    username = findViewById(R.id.username);
    et_password = findViewById(R.id.et_password);
    btn_login = findViewById(R.id.btn_login);
    btn_login.setOnClickListener(this);
    initView();
    initData();
  }

  private void initData() {


  }

  private void initView() {

  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.btn_login:
        if (!TextUtils.isEmpty(username.getText()) && !TextUtils.isEmpty(et_password.getText())){
          Toast.makeText(LoginActivity.this,"登陆成功！",Toast.LENGTH_SHORT).show();
          Intent intent = new Intent(LoginActivity.this,MainViewActivity.class);
          startActivity(intent);
          finish();
        }else
          Toast.makeText(LoginActivity.this,"请输入用户名或密码",Toast.LENGTH_SHORT).show();
        break;
    }
  }
}

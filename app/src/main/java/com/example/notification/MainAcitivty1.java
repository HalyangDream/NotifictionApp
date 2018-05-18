package com.example.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainAcitivty1 extends AppCompatActivity implements View.OnClickListener {

    private Button btn, btn1, btn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setLintener();
    }

    private void initView() {
        getSupportActionBar().setTitle("MainAcitivty1");
        btn = findViewById(R.id.btnNotification);
        btn1 = findViewById(R.id.btnNotification1);
        btn2 = findViewById(R.id.btnNotification2);
    }

    private void setLintener() {
        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNotification:
                NotificationUtils.getInstance(this).sendNotification();
                break;
            case R.id.btnNotification1:
                NotificationUtils.getInstance(this).sendNotification1();
                break;
            case R.id.btnNotification2:
                NotificationUtils.getInstance(this).sendNotification2();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_submit1:
                jump();
                break;
            case R.id.action_submit:
                Toast.makeText(this, "已经是Java版本了", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void jump() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

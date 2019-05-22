package com.example.notification;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

public class MainAcitivty1 extends AppCompatActivity implements View.OnClickListener {

    private Button btn, btn1, btn2;

    private NotificationService notificationService;
    private boolean isConnect = false;

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
        isNotificationListenerEnabled(this);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnect = true;
            NotificationService.MyBinder binder = (NotificationService.MyBinder) service;
            notificationService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnect = false;
        }
    };

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
                Intent intent = new Intent(this, NotificationService.class);
                intent.putExtra("type", "send");
                startService(intent);
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

    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public boolean isNotificationListenerEnabled(Context context) {
        Set<String> packageNames = NotificationManagerCompat.getEnabledListenerPackages(this);
        if (packageNames.contains(context.getPackageName())) {
//            Intent intent = new Intent(this, NotificationService.class);
//            bindService(intent, connection, Context.BIND_AUTO_CREATE);
            Intent intent = new Intent(context, NotificationService.class);
            intent.putExtra("type", "create");
            startService(intent);
            Toast.makeText(context, "permission auth", Toast.LENGTH_LONG).show();
            return true;
        }
        openNotificationListenSettings();
        return false;
    }

    public void openNotificationListenSettings() {
        try {
            Intent intent;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            } else {
                intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            }
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.example.notification;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class NotificationService extends NotificationListenerService {


    public class MyBinder extends Binder {
        NotificationService getService() {
            return NotificationService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("NotificationService", "onBind");
        return super.onBind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i("NotificationService", "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
//        super.onNotificationPosted(sbn);
        Log.i("NotificationService", "onNotificationPosted");

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
//        super.onNotificationRemoved(sbn);
        Log.i("NotificationService", "onNotificationRemoved");
    }

    @Override
    public void onListenerConnected() {
//        super.onListenerConnected();
        Log.i("NotificationService", "onListenerConnected");
    }

    @Override
    public void onListenerDisconnected() {
//        super.onListenerDisconnected();
        Log.i("NotificationService", "onListenerDisconnected");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String type = intent.getStringExtra("type");
        int num = 0;
        if (type.equals("send")) {
            StatusBarNotification[] statusBarNotifications = getActiveNotifications();
            Log.i("NotificationService", "statusBarNotifications num=" + statusBarNotifications.length);
            for (int i = 0; i < statusBarNotifications.length; i++) {
                StatusBarNotification item = statusBarNotifications[i];
                Log.i("NotificationService", "StatusBarNotification itemId=" + item.getId());
                if (item.getPackageName().equals("com.example.notification") && item.getId() == 1) {
                    Bundle bundle = item.getNotification().extras;
                    num = bundle.getInt("num", 0);
                    num += 1;
                }
            }
            Bundle bundle = new Bundle();
            bundle.putInt("num", num);
            String content = num > 0 ? "有" + num + "条内容" : "这是内容";
            NotificationUtils.getInstance(this).sendNotification1(content, bundle);
        }
        return super.onStartCommand(intent, flags, startId);
    }
}

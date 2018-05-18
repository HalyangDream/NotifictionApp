package com.example.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;


public class NotificationUtils {

    private Context mContext;

    private static NotificationUtils instance = null;

    private NotificationUtils(Context context) {
        this.mContext = context;
    }

    public static NotificationUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (NotificationUtils.class) {
                if (instance == null) {
                    instance = new NotificationUtils(context);
                }
            }
        }
        return instance;
    }


    private NotificationManager manager = null;

    /**
     * 获取NotificationManager的对象
     */
    private NotificationManager getManager(Context context) {
        if (manager == null) {
            manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    /**
     * 为了兼容8.0
     * 因为8.0的系统需要给Notification消息设置一个渠道
     * 在notity的时候给对应的channel发送
     */
    public void createChnnel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("test", "测试", NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setShowBadge(true);
            getManager(mContext).createNotificationChannel(channel);

        }
    }

    public void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, "chat")
                .setVisibility(NotificationCompat.VISIBILITY_SECRET)
                //必须
                .setSmallIcon(R.mipmap.ic_launcher)
                //必须
                .setContentTitle("这是标题")
                //必须
                .setContentText("这是内容")
                //必须
                .setContentIntent(getPendingIntent())
                .setAutoCancel(true)
                .setTimeoutAfter(3000)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .addAction(new NotificationCompat.Action(R.mipmap.ic_launcher, "查看", getPendingIntent()))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        getManager(mContext).notify(1, builder.build());
    }


    public void sendNotification1() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, "chat")
                .setVisibility(NotificationCompat.VISIBILITY_SECRET)
                //必须
                .setSmallIcon(R.mipmap.ic_launcher)
                //必须
                .setContentTitle("这是标题")
                //必须
                .setContentText("这是内容")
                //必须
                .setContentIntent(getPendingIntent())
                .setTicker("悬浮通知")
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .addAction(new NotificationCompat.Action(R.mipmap.ic_launcher, "查看", getPendingIntent()))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        getManager(mContext).notify(1, builder.build());
    }


    public void sendNotification2() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, "chat")
                .setVisibility(NotificationCompat.VISIBILITY_SECRET)
                //必须
                .setSmallIcon(R.mipmap.ic_launcher)
                //必须
                .setContentTitle("这是标题")
                //必须
                .setContentText("这是内容")
                //必须
                .setContentIntent(getPendingIntent())
                .setFullScreenIntent(getPendingIntent(), true)
                .setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .addAction(new NotificationCompat.Action(R.mipmap.ic_launcher, "查看", getPendingIntent()))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        getManager(mContext).notify(1, builder.build());
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(mContext, TestAcitivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("source", "Java");
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

}

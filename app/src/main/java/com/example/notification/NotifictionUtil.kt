package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat

class NotifictionUtil private constructor(context: Context) {

    private var mContext: Context = context

    companion object {
        @Volatile
        var instance: NotifictionUtil? = null

        fun getInstance(context: Context): NotifictionUtil {
            if (instance == null) {
                synchronized(NotifictionUtil::class) {
                    if (null == instance) {
                        instance = NotifictionUtil(context)

                    }
                }
            }
            return instance!!
        }
    }


    private var manager: NotificationManager? = null

    /**
     * 获取NotificationManager的对象
     */
    private fun getManager(context: Context): NotificationManager {
        if (manager == null) {
            manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        }
        return manager!!
    }

    /**
     * 为了兼容8.0
     * 因为8.0的系统需要给Notification消息设置一个渠道
     * 在notity的时候给对应的channel发送
     */
    fun createChnnel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("test", "测试", NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(true) //是否在桌面icon右上角展示小红点
            channel.lightColor = Color.GREEN //小红点颜色
            channel.setShowBadge(true)
            getManager(mContext).createNotificationChannel(channel)
        }
    }

    fun sendNotification() {
        val builder = NotificationCompat.Builder(mContext, "chat")
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
                .addAction(NotificationCompat.Action(R.mipmap.ic_launcher, "查看", getPendingIntent()))
        getManager(mContext).notify(1, builder.build())
    }


    fun sendNotification1() {
        val builder = NotificationCompat.Builder(mContext, "chat")
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
                .addAction(NotificationCompat.Action(R.mipmap.ic_launcher, "查看", getPendingIntent()))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
        getManager(mContext).notify(1, builder.build())
    }


    fun sendNotification2() {
        val builder = NotificationCompat.Builder(mContext, "chat")
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
                .addAction(NotificationCompat.Action(R.mipmap.ic_launcher, "查看", getPendingIntent()))
        getManager(mContext).notify(1, builder.build())
    }

    private fun getPendingIntent(): PendingIntent {
        val intent = Intent(mContext, TestAcitivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("source", "Kotlin")
        val pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        return pendingIntent
    }


}
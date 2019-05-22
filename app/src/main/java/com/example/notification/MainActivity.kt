package com.example.notification

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //本来想写在NotifictionUtil的私有构造里的，但是kotlin的语法不是很熟就先写在这(Android8.x必须要这个)
        NotifictionUtil.getInstance(this).createChnnel()
        supportActionBar?.title = "MainActivity"
        setLintener()
//        val intent = Intent(this, NotificationService::class.java)
//        startService(intent)
    }

    private fun setLintener() {
        btnNotification.setOnClickListener(this)
        btnNotification1.setOnClickListener(this)
        btnNotification2.setOnClickListener(this)
    }

    /**
     * 通知需要打开权限(这个一般都是打开的)
     * 悬浮通知除了需要通知权限，还需要悬浮权限(小米的叫悬浮通知，华为的叫横幅)，也有部分机型是自带的哦
     */
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnNotification ->
                NotifictionUtil.getInstance(this).sendNotification()
            R.id.btnNotification1 ->
                NotifictionUtil.getInstance(this).sendNotification1()
            R.id.btnNotification2 ->
                NotifictionUtil.getInstance(this).sendNotification2()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_info, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_submit ->
                jump()
            R.id.action_submit1 ->
                Toast.makeText(this, "已经是Kotlin版本了", Toast.LENGTH_LONG).show();

        }

        return super.onOptionsItemSelected(item)
    }

    private fun jump() {
        var intent = Intent(this, MainAcitivty1::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }
}

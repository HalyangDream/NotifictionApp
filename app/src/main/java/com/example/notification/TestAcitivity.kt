package com.example.notification

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test.*

class TestAcitivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        supportActionBar?.title = "TestAcitivity"
        getData()
    }

    private fun getData() {
        val temp = intent.getStringExtra("source")
        source.text = temp
    }


}
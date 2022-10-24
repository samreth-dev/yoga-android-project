package com.example.yoga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navBtnYoga()
    }

    fun navBtnYoga() {
        // set yoga fragment to frame view
        val fragment = YogaFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }


}
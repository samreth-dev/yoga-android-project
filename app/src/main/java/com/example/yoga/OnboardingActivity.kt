package com.example.yoga

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.onboard_viewpager)
        viewPager.adapter = OnboardingPagerAdapter(supportFragmentManager, lifecycle)

        val tabLayout = findViewById<TabLayout>(R.id.tab_indicator)
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        val nextBnt = findViewById<Button>(R.id.btn_next)
        nextBnt.setOnClickListener {
            val position = viewPager.currentItem
            if (position == 2) {

            }
        }
    }
}
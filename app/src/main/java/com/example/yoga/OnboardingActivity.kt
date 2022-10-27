package com.example.yoga

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_onboarding.*
import kotlinx.android.synthetic.main.activity_onboarding.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private var tabIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        isNew()

        viewPager = findViewById(R.id.onboard_viewpager)
        viewPager.adapter = OnboardingPagerAdapter(supportFragmentManager, lifecycle)

        val tabLayout = findViewById<TabLayout>(R.id.tab_indicator)
        TabLayoutMediator(tabLayout, viewPager) { _, _ ->
        }.attach()

        btn_next.setOnClickListener {
            tabLayout.getTabAt(++tabIndex)?.select()
        }

    }

    private fun isNew() {
        try {
            var getS = getSharedPreferences("profile", MODE_PRIVATE)
            if (getS.getBoolean("isnew", false)) {
                finish()
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        catch (e: Exception) {
        }
    }

}
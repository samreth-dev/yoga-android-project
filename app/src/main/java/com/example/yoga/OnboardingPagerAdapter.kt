package com.example.yoga

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingPagerAdapter(fa: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fa, lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        if (position == 0)
            return YogaIntroFragment()

        if (position == 1) {
            return BMIIntroFragment()
        }

        return CreateProfileFragment()
    }

}
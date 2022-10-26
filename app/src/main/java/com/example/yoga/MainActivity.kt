package com.example.yoga


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.annotation.SuppressLint
import android.app.Application
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*


class MainActivity : AppCompatActivity() {

    private var isEdited = false
    lateinit var mnavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var manager = supportFragmentManager
        var transaction = manager.beginTransaction()
        transaction.add(R.id.fcvMain, ArticleFragment())
        transaction.commit()
        bnvMain.setOnItemSelectedListener {
            try {
                when (it.itemId) {
                    R.id.navBtnSetting -> {
                        transaction = manager.beginTransaction()
                        transaction.replace(R.id.fcvMain, ProfileFragment())
                        transaction.commit()
//                        val current = mnavController.currentDestination?.id
//                        if (current == R.id.articleFragment) {
//                            mnavController.navigate(R.id.action_articleFragment_to_profileFragment)
//                        }
//                        else if (current == R.id.articleDetailFragment){
//                            mnavController.navigate(R.id.action_articleDetailFragment_to_profileFragment)
//                        }
                    }
                    R.id.navBtnArticle -> {
//                        mnavController.navigate(R.id.action_profileFragment_to_articleFragment)
                        transaction = manager.beginTransaction()
                        transaction.replace(R.id.fcvMain, ArticleFragment())
                        transaction.commit()
                    }
                }
            }
            catch (e: Exception) {
            }
            true
        }
    }


}
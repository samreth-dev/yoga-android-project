package com.example.yoga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var isEdited = false
    lateinit var mnavController: NavController
    private var articleFragment = ArticleFragment()
    private var calorieLostFragment = CalorieLostFragment()
    private var yogaIntroFragment = YogaIntroFragment()
    private var profileFragment = ProfileFragment()
    private var articleDetailFragment = ArticleDetailFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTheme(getPreferences(MODE_PRIVATE).getInt("theme", R.style.Theme_Yoga))

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
                    }
                    R.id.navBtnArticle -> {
                        transaction = manager.beginTransaction()
                        transaction.replace(R.id.fcvMain, ArticleFragment())
                        transaction.commit()
                    }

                    R.id.navBtnCalculator -> {
                        transaction = manager.beginTransaction()
                        transaction.replace(R.id.fcvMain, CalculatorFragment())
                        transaction.commit()
                    }
                    R.id.navBtnYoga -> {
                        transaction = manager.beginTransaction()
                        transaction.replace(R.id.fcvMain, YogaFragment())
                        transaction.commit()
                    }

                    R.id.navBtnStatistic -> {
                        transaction = manager.beginTransaction()
                        transaction.replace(R.id.fcvMain, StatisticsFragment())
                        transaction.commit()

//                        val fragment = StatisticsFragment()
//                        val transaction = supportFragmentManager.beginTransaction()
//                        transaction.add(R.id.fcvMain, fragment)
//                        transaction.commit()

                        // PopupSettingsFragment().show(supportFragmentManager, PopupSettingsFragment.TAG)
                    }
                }
            }
            catch (e: Exception) {
            }
            true
        }

        PopupSettingsFragment().show(supportFragmentManager, PopupSettingsFragment.TAG)

    }

}
package com.example.yoga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


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

        val localeCode = getPreferences(MODE_PRIVATE).getString("locale", resources.configuration.locale.toLanguageTag().substring(0, 2)) ?: "en"
        val locale = Locale(localeCode)
        val config = resources.configuration
        Locale.setDefault(locale)
        config.locale = locale
        // Update new locale settings
        resources.updateConfiguration(config, resources.displayMetrics)

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
                        transaction.replace(R.id.fcvMain, CalorieLostFragment())
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
                    }
                }
            }
            catch (e: Exception) {
            }
            true
        }

    }

}
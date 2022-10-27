package com.example.yoga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

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
                    }
                }
            }
            catch (e: Exception) {
            }
            true
        }

    }

}



//        setTheme(getPreferences(MODE_PRIVATE).getInt("theme", R.style.Theme_Yoga))

//        val localeCode = getPreferences(MODE_PRIVATE).getString("locale", resources.configuration.locale.toLanguageTag().substring(0, 2)) ?: "en"
//        val locale = Locale(localeCode)
//        val config = resources.configuration
//        Locale.setDefault(locale)
//        config.locale = locale
//        resources.updateConfiguration(config, resources.displayMetrics)
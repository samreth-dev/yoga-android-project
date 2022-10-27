package com.example.yoga

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.math.MathUtils.clamp
import com.google.android.material.math.MathUtils
import kotlinx.android.synthetic.main.fragment_statistics.view.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*
import kotlin.math.roundToInt

class StatisticsFragment : BaseCoroutineFragment() {

    var caloriesBurned = 0.0
    var timeElapsed = 0.0

    var caloriesBurnedTarget = 0
    var timeElapsedTarget = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_statistics, container, false)

        fetchTargets()

        caloriesBurned = 0.0
        timeElapsed = 0.0

        launch{
            context?.let {
                YogaDatabase(it).getYogaSessionDao().getTotalBurnedCalories(LocalDateTime.now().minusDays(1), LocalDateTime.now()).let {
                    caloriesBurned = it
                }
                YogaDatabase(it).getYogaSessionDao().getTotalYogaDuration(LocalDateTime.now().minusDays(1), LocalDateTime.now()).let {
                    timeElapsed = it
                }
                activity?.runOnUiThread{
                    updateProgressBars(view)
                }
            }
        }

        updateProgressBars(view)

        view.statisticsDailyCaloriesBurnedTextInputEditText.setText(caloriesBurnedTarget.toString())
        view.statisticsDailyTimeTextInputEditText.setText(timeElapsedTarget.toString())

        view.statisticsSaveButton.setOnClickListener { onSaveStatisticsButtonClicked() }

        return view
    }

    @SuppressLint("SetTextI18n")
    fun updateProgressBars(view: View){
        val caloriesProgress = clamp(caloriesBurned.toFloat() / caloriesBurnedTarget, 0f, 1f)
        val timeElapsedProgress = clamp(timeElapsed.toFloat() / timeElapsedTarget, 0f, 1f)

        view.caloriesBurnedProgressBar?.startAngle = ACTIVE_ANGLE
        view.caloriesBurnedProgressBar?.setProgressWithAnimation(MathUtils.lerp(0f, MAX_PROGRESS_PERCENTAGE, caloriesProgress), 1000)
        view.caloriesBurnedAmount?.text = "${(caloriesBurned * 100.0).roundToInt() / 100.0} / $caloriesBurnedTarget"

        view.timeElapsedDailyProgressBar?.startAngle = ACTIVE_ANGLE
        view.timeElapsedDailyProgressBar?.setProgressWithAnimation(MathUtils.lerp(0f, MAX_PROGRESS_PERCENTAGE, timeElapsedProgress), 1000)
        view.timeElapsedAmount?.text = "${(timeElapsed * 100.0).roundToInt() / 100.0} / $timeElapsedTarget"
    }

    fun fetchTargets(){
        val prefs = requireActivity().getSharedPreferences("statisticsSettings", Context.MODE_PRIVATE)
        caloriesBurnedTarget = prefs.getInt("caloriesBurned", 300)
        timeElapsedTarget = prefs.getInt("timeElapsed", 60)
    }

    fun savePreferences(){
        val prefs = requireActivity().getSharedPreferences("statisticsSettings", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putInt("caloriesBurned", caloriesBurnedTarget)
        editor.putInt("timeElapsed", timeElapsedTarget)
        editor.apply()
        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
    }

    fun onSaveStatisticsButtonClicked(){
        caloriesBurnedTarget = view?.statisticsDailyCaloriesBurnedTextInputEditText?.text.toString().toInt()
        timeElapsedTarget = view?.statisticsDailyTimeTextInputEditText?.text.toString().toInt()
        savePreferences()
        view?.let { updateProgressBars(it) }
    }

    companion object {
        const val PASSIVE_ANGLE = 120f
        const val ACTIVE_ANGLE = 360f - PASSIVE_ANGLE
        const val MAX_PROGRESS_PERCENTAGE = (ACTIVE_ANGLE / 360f) * 100f
    }
}
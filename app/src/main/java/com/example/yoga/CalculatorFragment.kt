package com.example.yoga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.android.synthetic.main.fragment_calculator.view.*
import kotlin.math.roundToInt


class CalculatorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_calculator, container, false)

        view.btnCalculate.setOnClickListener {
            val result = etDuration.text.toString().toDouble() * etWeight.text.toString().toDouble() * 0.0175 * 2.5
            view.tvCongrat.setText("CONGRATS! YOU HAVE LOST:")
            view.tvResult.setText(result.roundToInt().toString() + " CALORIES")
            view.etDuration.clearFocus()
            view.etWeight.clearFocus()
        }

        return view

    }


}
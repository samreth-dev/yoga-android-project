package com.example.yoga

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.yoga.databinding.FragmentArticleBinding
import com.example.yoga.databinding.FragmentArticleDetailBinding
import com.example.yoga.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var isEdited = false
        var tempAge = 0
        var tempWeight = 0
        var tempHeight = 0

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.apply {
            fabProfileEdit.backgroundTintList = ColorStateList.valueOf(Color.rgb(107,197, 237))
            fabProfileEdit.setOnClickListener {
            isEdited = !isEdited
            if (isEdited) {
                fabProfileEdit.setImageResource(R.drawable.ic_save_edit)
                etProfileName.isEnabled = true
                etProfileHeight.isEnabled = true
                etProfileWeight.isEnabled = true
                etProfileAge.isEnabled = true
                tempAge = etProfileAge.text.toString().removeSuffix(" Y.os").toInt()
                tempWeight = etProfileWeight.text.toString().removeSuffix(" Kg").toInt()
                tempHeight = etProfileHeight.text.toString().removeSuffix(" Cm").toInt()
                etProfileAge.setText(tempAge.toString())
                etProfileWeight.setText(tempWeight.toString())
                etProfileHeight.setText(tempHeight.toString())
                fabProfileEdit.backgroundTintList = ColorStateList.valueOf(Color.rgb(255,90, 90))
                Toast.makeText(context, "Edit mode!", Toast.LENGTH_SHORT).show()
            }
            else {
                fabProfileEdit.setImageResource(R.drawable.ic_edit)
                etProfileName.isEnabled = false
                etProfileHeight.isEnabled = false
                etProfileWeight.isEnabled = false
                etProfileAge.isEnabled = false
                etProfileHeight.setText(etProfileHeight.text.toString() + " Cm")
                etProfileAge.setText(etProfileAge.text.toString() + " Y.os")
                etProfileWeight.setText(etProfileWeight.text.toString() + " Kg")
                fabProfileEdit.backgroundTintList = ColorStateList.valueOf(Color.rgb(107,197, 237))
                Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
            }
            }

        }

        return binding.root
    }

}
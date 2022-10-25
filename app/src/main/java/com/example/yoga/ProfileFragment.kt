package com.example.yoga

import android.R
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.yoga.databinding.FragmentProfileBinding
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var startforResultGalley : ActivityResultLauncher<Intent>
    private var isEdited = false
    private var tempAge = 0
    private var tempWeight = 0
    private var tempHeight = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        setImageButtonListener()
        initSP()
        setEditButtonListener()

        return binding.root
    }

    private fun initSP() {
        try {
            binding.apply {
                var getS = context?.getSharedPreferences("profile", MODE_PRIVATE)
                etProfileName.setText(getS?.getString("name", ""))
                etProfileAge.setText(getS?.getString("age", ""))
                etProfileHeight.setText(getS?.getString("height", ""))
                etProfileWeight.setText(getS?.getString("weight", ""))
            }
        }
        catch (e: java.lang.Exception) {
        }
    }

    private fun setEditButtonListener() {
        binding.apply {
            fabProfileEdit.backgroundTintList = ColorStateList.valueOf(Color.rgb(107,197, 237))
            fabProfileEdit.setOnClickListener {
                isEdited = !isEdited
                if (isEdited) {
                    editMode()
                }
                else {
                    saveMode()
                }
            }
        }
    }

    private fun setImageButtonListener() {
        binding.apply {
            startforResultGalley =  registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if(it!=null)
                    imgProfile.setImageURI(it.data?.data)

            }
            imgProfile.setOnClickListener {
                val i = Intent()
                i.action = Intent.ACTION_PICK
                i.type = "image/*"
                startforResultGalley.launch(i)
            }
        }
    }

    private fun editMode() {
        binding.apply {
            fabProfileEdit.setImageResource(R.drawable.ic_)
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
    }

    private fun saveMode() {

        val editS = context?.getSharedPreferences("profile", MODE_PRIVATE)?.edit()

        binding.apply {
            fabProfileEdit.setImageResource(R.drawable.ic_save_edit)
            etProfileName.isEnabled = false
            etProfileHeight.isEnabled = false
            etProfileWeight.isEnabled = false
            etProfileAge.isEnabled = false
            etProfileHeight.setText(etProfileHeight.text.toString() + " Cm")
            etProfileAge.setText(etProfileAge.text.toString() + " Y.os")
            etProfileWeight.setText(etProfileWeight.text.toString() + " Kg")
            try {
                editS?.putString("name", etProfileName.text.toString())
                editS?.putString("age", etProfileAge.text.toString())
                editS?.putString("height", etProfileHeight.text.toString())
                editS?.putString("weight", etProfileWeight.text.toString())
                editS?.commit()
            }
            catch (e: Exception) {
            }
            fabProfileEdit.backgroundTintList = ColorStateList.valueOf(Color.rgb(107,197, 237))
            Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
        }
    }
}
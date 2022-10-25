package com.example.yoga


import android.R.attr.data
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
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
import java.io.ByteArrayOutputStream


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
            binding.apply {
                etProfileName.setText("Name")
                etProfileAge.setText("")
                etProfileHeight.setText("")
                etProfileWeight.setText("")
            }
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
                if(it!=null) {
//                    imgProfile.setImageURI(it.data?.data)
//                    val photo = it.data?.data
//                    val baos = ByteArrayOutputStream()
//                    photo.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//                    val b: ByteArray = baos.toByteArray()
//                    val encodedImage: String = Base64.encodeToString(b, Base64.DEFAULT)
//                    preferenceManager.setString("image_data", encodedImage)
                }




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
            fabProfileEdit.setImageResource(R.drawable.ic_save_edit)
            etProfileName.isEnabled = true
            etProfileHeight.isEnabled = true
            etProfileWeight.isEnabled = true
            etProfileAge.isEnabled = true
            try {
                tempAge = etProfileAge.text.toString().removeSuffix(" Y.os").toInt()
                tempWeight = etProfileWeight.text.toString().removeSuffix(" Kg").toInt()
                tempHeight = etProfileHeight.text.toString().removeSuffix(" Cm").toInt()
            }
            catch (e: Exception) {
            }
            etProfileAge.setText(tempAge.toString())
            etProfileWeight.setText(tempWeight.toString())
            etProfileHeight.setText(tempHeight.toString())
            fabProfileEdit.backgroundTintList = ColorStateList.valueOf(Color.rgb(255,90, 90))
            Toast.makeText(context, "Edit mode!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveMode() {
        binding.apply {
            val editS = context?.getSharedPreferences("profile", MODE_PRIVATE)?.edit()
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
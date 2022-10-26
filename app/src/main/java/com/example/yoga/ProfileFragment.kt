package com.example.yoga

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_profile.view.*
import java.io.ByteArrayOutputStream

class ProfileFragment : Fragment() {

    //private lateinit var binding: FragmentProfileBinding
    private lateinit var startforResultGalley : ActivityResultLauncher<Intent>
    private var encodedImg = ""
    private var isEdited = false
    private var tempAge = 0
    private var tempWeight = 0
    private var tempHeight = 0
    private lateinit var viewF: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewF =  inflater.inflate(R.layout.fragment_profile, container, false)

        initSP()
        setImageButtonListener()
        setEditButtonListener()

        return viewF
//        binding = FragmentProfileBinding.inflate(inflater, container, false)
//
//        initSP()
//        setImageButtonListener()
//        setEditButtonListener()
//
//        return binding.root
    }

    private fun initSP() {
        try {
            viewF.apply {
                var getS = viewF.context.getSharedPreferences("profile", MODE_PRIVATE)
                etProfileName.setText(getS?.getString("name", ""))
                etProfileAge.setText(getS?.getString("age", ""))
                etProfileHeight.setText(getS?.getString("height", ""))
                etProfileWeight.setText(getS?.getString("weight", ""))
                encodedImg = getS?.getString("img", "").toString()
                val decodedString: ByteArray = Base64.decode(encodedImg, Base64.DEFAULT)
                val bitmapImg =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                imgProfile.setImageBitmap(bitmapImg)
            }
        }
        catch (e: java.lang.Exception) {
            viewF.apply {
                etProfileName.setText("Name")
                etProfileAge.setText("")
                etProfileHeight.setText("")
                etProfileWeight.setText("")
            }
        }
    }

    private fun setEditButtonListener() {
        viewF.apply {
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
        viewF.apply {
            startforResultGalley =  registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if(it!=null) {
                    val img = it.data?.data
                    val bitmapImg =
                        MediaStore.Images.Media.getBitmap(context?.getContentResolver(), img)
                    imgProfile.setImageBitmap(bitmapImg)
                    val baos = ByteArrayOutputStream()
                    bitmapImg.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    val b = baos.toByteArray()
                    encodedImg = android.util.Base64.encodeToString(b, android.util.Base64.DEFAULT)
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
        viewF.apply {
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
        viewF.apply {
            val editS = viewF.context?.getSharedPreferences("profile", MODE_PRIVATE)?.edit()
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
                editS?.putString("img", encodedImg)
                editS?.commit()
            }
            catch (e: Exception) {
            }
            fabProfileEdit.backgroundTintList = ColorStateList.valueOf(Color.rgb(107,197, 237))
            Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
        }
    }

}
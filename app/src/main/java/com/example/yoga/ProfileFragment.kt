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

    private lateinit var startforResultGalley : ActivityResultLauncher<Intent>
    private var encodedImg = ""
    private var isEdited = false
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

    }

    private fun initSP() {
        try {
            viewF.apply {
                var getS = viewF.context.getSharedPreferences("profile", MODE_PRIVATE)
                etProfileName.setText(getS?.getString("name", ""))
                etProfileWeight.setText(getS?.getString("weight", ""))
                encodedImg = getS?.getString("img", "").toString()
                val decodedString: ByteArray = Base64.decode(encodedImg, Base64.DEFAULT)
                val bitmapImg =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                imgProfile.setImageBitmap(bitmapImg)
                if (decodedString.isEmpty()) {
                    imgProfile.setImageResource(R.drawable.img_temp)
                }
            }
        }
        catch (e: java.lang.Exception) {
            viewF.apply {
                etProfileName.setText("")
                etProfileWeight.setText("")
                imgProfile.setImageResource(R.drawable.img_temp)
            }
        }
    }

    private fun setEditButtonListener() {
        viewF.apply {
            fabProfileEdit.backgroundTintList = ColorStateList.valueOf(Color.rgb(10,34, 27))
            fabProfileEdit.setOnClickListener {
                var dontdo = false
                if (etProfileName.text.toString().isNullOrEmpty() || etProfileWeight.text.toString().isNullOrEmpty()) {
                    Toast.makeText(context, "Name or Weight must not be empty!", Toast.LENGTH_SHORT).show()
                    dontdo = true
                }
                if (!dontdo) {
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
    }

    private fun setImageButtonListener() {
        viewF.apply {
            try {
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
                    if (isEdited) {
                        val i = Intent()
                        i.action = Intent.ACTION_PICK
                        i.type = "image/*"
                        startforResultGalley.launch(i)
                    }
                }
            }
            catch (e: Exception) {
            }
        }
    }

    private fun editMode() {
        viewF.apply {
            fabProfileEdit.setImageResource(R.drawable.ic_save_edit)
            etProfileName.isEnabled = true
            etProfileWeight.isEnabled = true
            fabProfileEdit.backgroundTintList = ColorStateList.valueOf(Color.rgb(255,90, 90))
            Toast.makeText(context, "Edit mode!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveMode() {
        viewF.apply {
            val editS = viewF.context?.getSharedPreferences("profile", MODE_PRIVATE)?.edit()
            fabProfileEdit.setImageResource(R.drawable.ic_edit)
            etProfileName.isEnabled = false
            etProfileWeight.isEnabled = false
            try {
                editS?.putString("name", etProfileName.text.toString())
                editS?.putString("weight", etProfileWeight.text.toString())
                editS?.putString("img", encodedImg)
                editS?.commit()
            }
            catch (e: Exception) {
            }
            fabProfileEdit.backgroundTintList = ColorStateList.valueOf(Color.rgb(10,34, 27))
            Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
        }
    }

}
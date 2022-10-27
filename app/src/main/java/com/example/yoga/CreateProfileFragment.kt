package com.example.yoga

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_create_profile.*
import kotlinx.android.synthetic.main.fragment_create_profile.view.*

class CreateProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_create_profile, container, false)

        view.btnConfirm.setOnClickListener {
            var dont = false
            if (etOnName.text.toString().isNullOrEmpty() || etOnWeight.text.toString().isNullOrEmpty()) {
                dont = true
                Toast.makeText(context, "Name or Weight must not be empty!", Toast.LENGTH_SHORT).show()
            }
            if (!dont) {
                var getS = view.context.getSharedPreferences("profile", AppCompatActivity.MODE_PRIVATE)
                var edit = getS.edit()
                edit.putString("name", etOnName.text.toString())
                edit.putString("weight", etOnWeight.text.toString())
                edit.putBoolean("isnew", true)
                edit.apply()
                var intent = Intent(context, MainActivity::class.java)
                context?.startActivity(intent)
            }
        }

        return view

    }

}
package com.example.yoga

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson

import com.google.gson.stream.JsonReader

import kotlinx.android.synthetic.main.fragment_yoga.view.*
import kotlinx.android.synthetic.main.yoga_type_list.*
import java.io.StringReader

class YogaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_yoga, container, false)

        val gson = Gson()

        val yogas = ArrayList<Yoga>()

        val yogaJson = gson.fromJson<YogaJSON>(activity?.assets?.open("yogas.json")?.bufferedReader(), YogaJSON::class.java)

        yogaJson.yogas.forEach {
            yogas.add(it)
        }


        view.recyclerView1.layoutManager = LinearLayoutManager(context)
        val adapter = MyAdapter(yogas)
        view.recyclerView1.adapter = adapter

        //when clicked go to video fragment
        adapter.onItemClick = { yoga ->
            val fragment = VideoFragment.newInstance(yoga)
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fcvMain, fragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
        return view
    }

}
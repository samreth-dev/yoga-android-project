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
        // Inflate the layout for this fragment
        val gson = Gson()

        val yogas = ArrayList<Yoga>()
//        var prefs = activity?.getSharedPreferences("yoga", 0)
//        var editor = prefs?.edit()
//        Yoga(
//            "Beginner",
//            "Slower paced to develop clear safe alignment in basic poses.",
//            listOf(Video("v1", "video1"), Video("v2", "video2"),
//                Video("v3", "video3"))
//        ).apply {
//            editor?.putString("beginner", gson.toJson(this).toString())
//            editor?.apply()
//        }
//
//        Yoga(
//            "Intermediate",
//            "A little faster paced to develop strength and flexibility.",
//            listOf(Video("v4", "video4"), Video("v5", "video5"),
//                Video("v6", "video6"))
//        ).apply {
//            editor?.putString("intermediate", gson.toJson(this).toString())
//            editor?.apply()
//        }
//
//        Yoga(
//            "Advanced",
//            "A little faster paced to develop strength and flexibility.",
//            listOf(Video("v9", "video9"))
//        ).apply {
//            editor?.putString("advanced", gson.toJson(this).toString())
//            editor?.apply()
//        }

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
            transaction?.replace(R.id.frameLayout, fragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
        return view
    }

}
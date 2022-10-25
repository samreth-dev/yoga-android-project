package com.example.yoga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_yoga.view.*
import kotlinx.android.synthetic.main.yoga_type_list.*

class YogaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_yoga, container, false)
        // Inflate the layout for this fragment
        val gson = Gson()

        val yogas = ArrayList<Yoga>()
        var prefs = activity?.getSharedPreferences("yoga", 0)
        var editor = prefs?.edit()
        Yoga(
            "Beginner",
            "Slower paced to develop clear safe alignment in basic poses.",
            listOf(Video("Yayyy", "video1"), Video("Yayyy2", "video2"))
        ).apply {
            editor?.putString("beginner", gson.toJson(this).toString())
            editor?.apply()
        }

        yogas.add(
            Yoga(
                "Beginner",
                "Slower paced to develop clear safe alignment in basic poses.",
                ArrayList()
            )
        )
        yogas.add(
            Yoga(
                "Intermediate",
                "More physically challenging than beginner yoga and multi level.",
                ArrayList()
            )
        )
        yogas.add(
            Yoga(
                "Advanced",
                "More athletic move through asana at a fast pace, with no instruction.",
                ArrayList()
            )
        )


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
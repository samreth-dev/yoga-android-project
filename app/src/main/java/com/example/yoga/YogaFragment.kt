package com.example.yoga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_yoga.view.*

class YogaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_yoga, container, false)
        // Inflate the layout for this fragment
        val yogas = ArrayList<Yoga>()
        yogas.add(Yoga("Yoga for Beginners", "Beginner desc", R.drawable.beginner))
        yogas.add(Yoga("Yoga for Intermediate", "Intermediate desc", R.drawable.intermediate))
        yogas.add(Yoga("Yoga for Advanced", "Advanced desc", R.drawable.advanced))


        view.recyclerView1.layoutManager = LinearLayoutManager(context)
        val adapter = MyAdapter(yogas)
        view.recyclerView1.adapter = adapter

        return view
    }

}
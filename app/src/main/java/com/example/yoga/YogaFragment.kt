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
        yogas.add(Yoga("ASANA Yoga for Beginners", "Slower paced and focuses on developing clear and safe alignment in foundational poses", R.drawable.beginner))
        yogas.add(Yoga("ASANA Yoga for Intermediates", "More physically challenging than beginner yoga and multi level.", R.drawable.intermediate))
        yogas.add(Yoga("Yoga for Advanced", "The lesson will be more athletic and move through asana at a fast pace, with very little instruction.", R.drawable.advanced))


        view.recyclerView1.layoutManager = LinearLayoutManager(context)
        val adapter = MyAdapter(yogas)
        view.recyclerView1.adapter = adapter

        //when clicked go to video fragment
        val vfragment = VideoFragment()
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frameLayout, vfragment)
            ?.commit()

        return view
    }

}
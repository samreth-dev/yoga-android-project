package com.example.yoga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_yoga.view.*
import kotlinx.android.synthetic.main.yoga_type_list.*

class YogaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_yoga, container, false)
        // Inflate the layout for this fragment
        val yogas = ArrayList<Yoga>()

        yogas.add(
            Yoga(
                "Beginners",
                "Slower paced to develop clear safe alignment in basic poses.",
                ArrayList()
            )
        )
        yogas.add(
            Yoga(
                "Intermediates",
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
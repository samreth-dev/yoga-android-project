package com.example.yoga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

private const val ARG_PARAM1 = "yoga"

class VideoFragment : Fragment() {
    private var yoga: Yoga? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            yoga = it.getSerializable(ARG_PARAM1) as Yoga?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_video, container, false)
        // Inflate the layout for this fragment

        val videos = ArrayList<Video>()
        videos.add(Video("Yoga for Beginners", R.raw.video1))
        videos.add(Video("Yoga for Intermediates", R.raw.video2))

        Toast.makeText(context, yoga?.title, Toast.LENGTH_SHORT).show()
        return view


    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Yoga) =
            VideoFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}
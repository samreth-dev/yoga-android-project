package com.example.yoga

import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class VideoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_video, container, false)
        // Inflate the layout for this fragment

        val videos = ArrayList<Video>()
        videos.add(Video("Yoga for Beginners", R.raw.video1))
        videos.add(Video("Yoga for Intermediates", R.raw.video2))
        return view


    }

}
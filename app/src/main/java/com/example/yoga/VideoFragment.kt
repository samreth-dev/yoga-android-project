package com.example.yoga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_video.view.*

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
        val view = inflater.inflate(R.layout.fragment_video, container, false)

        Toast.makeText(context, yoga?.title, Toast.LENGTH_SHORT).show()

        val mediaController = MediaController(context)
        val videoView1 = view.videoView1
        mediaController.setAnchorView(videoView1)
        videoView1.setMediaController(mediaController)

        val rawId = resources.getIdentifier("video1", "raw", context?.packageName)

        videoView1.setVideoPath("android.resource://" + activity?.packageName + "/" + rawId)
        videoView1.start()
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
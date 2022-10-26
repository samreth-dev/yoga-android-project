package com.example.yoga

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_video.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis


private const val ARG_PARAM1 = "yoga"
private const val VIDEO_HOST = "https://silvanaishak.github.io/mdpVideos/"
private const val VIDEO_EXTENSION = ".mp4"

class VideoFragment : Fragment() {
    private var yoga: Yoga? = null
    private var currentVideoIndex: Int = -1

    private lateinit var mediaController: MediaController
    private lateinit var videoView: VideoView

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

        mediaController = MediaController(context)
        videoView = view.videoView
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.setOnPreparedListener {
            it.setOnVideoSizeChangedListener { mediaPlayer, i, i2 ->
                /*
                 * add media controller
                 */
                mediaController = MediaController(context)
                videoView.setMediaController(mediaController)
                /*
                 * and set its position on screen
                 */
                mediaController.setAnchorView(videoView)
            }

        }
        (videoView as PlayStateBroadcastingVideoView).setPlayPauseListener(object: PlayStateBroadcastingVideoView.PlayPauseListener {
            override fun onPlay() {
            }
            override fun onPause() {
            }
        })
        videoView.setOnCompletionListener {
            playNextVideo()
        }
        playNextVideo()
        view.btnNext.setOnClickListener {
            playNextVideo()
        }
        view.btnPrevious.setOnClickListener {
            playPreviousVideo()
        }
        GlobalScope.launch(Dispatchers.Main) {
            measureTimeMillis {
                updateDuration(view)
            }
        }
        return view
    }

    private suspend fun updateDuration(view: View){
        while (true) {
            if(videoView.isPlaying) {
                view.timerText.text = ((videoView.currentPosition) / 1000).toString()
            }
            delay(500)
        }
    }

    fun onVideosFinished() {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle("GREAT JOB!")
                .setMessage("You've finished ${yoga!!.title} successfully!").show()
        }
        activity?.onBackPressed()
    }

    fun playNextVideo() {
        if (currentVideoIndex + 1 >= (yoga?.video?.size ?: 0)) {
            onVideosFinished()
            return
        }
        currentVideoIndex++
        val video = yoga?.video?.get(currentVideoIndex)
        videoView.setVideoURI(Uri.parse(VIDEO_HOST + (video?.videoFileName) + VIDEO_EXTENSION))
        videoView.start()
    }

    fun playPreviousVideo() {
        if (currentVideoIndex - 1 < 0) {
            activity?.onBackPressed()
            return
        }
        currentVideoIndex--
        val video = yoga?.video?.get(currentVideoIndex)
        videoView.setVideoURI(Uri.parse(VIDEO_HOST + (video?.videoFileName) + VIDEO_EXTENSION))
        videoView.start()
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
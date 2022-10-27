package com.example.yoga

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.fragment_video.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.math.floor
import kotlin.system.measureTimeMillis


private const val ARG_PARAM1 = "yoga"
private const val VIDEO_HOST = "https://silvanaishak.github.io/mdpVideos/"
private const val VIDEO_EXTENSION = ".mp4"

class VideoFragment : BaseCoroutineFragment() {
    private var yoga: Yoga? = null
    private var currentVideoIndex: Int = -1
    private var watchedTime: Long = 0

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

        savedInstanceState?.getInt("currentVideoIndex")?.let {
            currentVideoIndex = (it - 1).coerceAtLeast(0)
        }
        savedInstanceState?.getSerializable("yoga")?.let {
            yoga = it as Yoga
        }
        savedInstanceState?.getLong("watchedTime")?.let {
            watchedTime = it
        }

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
                view.txtvideoname.text = yoga?.video?.get(currentVideoIndex)?.name ?: ""
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
            delay(100)
            if(videoView.isPlaying) {
                watchedTime += 100
                view.timerText.text = floor(watchedTime.toDouble()/1000).toInt().toString()
            }
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentVideoIndex", currentVideoIndex)
        outState.putSerializable("yoga", yoga)
        outState.putLong("watchedTime", watchedTime)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        launch {
            val totalTimeElapsed = ((watchedTime.toDouble() / 1000)/60)
            val yogaSession = YogaSession(burnedCalories = (totalTimeElapsed*3), duration = totalTimeElapsed, yogaCompletionDateTime = LocalDateTime.now())
            context?.let {
                YogaDatabase(it).getYogaSessionDao().addYogaSession(yogaSession)
            }
        }
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
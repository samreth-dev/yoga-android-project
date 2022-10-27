package com.example.yoga

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView

class PlayStateBroadcastingVideoView : VideoView {

    interface PlayPauseListener {
        fun onPlay()
        fun onPause()
    }

    private var mListener: PlayPauseListener? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, theme: Int) : super(
        context,
        attrs,
        theme
    )

    override fun pause() {
        super.pause()
        if (mListener != null) {
            mListener!!.onPause()
        }
    }

    override fun start() {
        super.start()
        if (mListener != null) {
            mListener!!.onPlay()
        }
    }

    fun setPlayPauseListener(listener: PlayPauseListener?) {
        mListener = listener
    }
}
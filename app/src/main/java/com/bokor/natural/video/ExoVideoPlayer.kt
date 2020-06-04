package com.bokor.natural.video

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bokor.natural.R
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_exo_video_player.*

class ExoVideoPlayer : AppCompatActivity() {

    private val videoFile = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"

    private var mPlayer : SimpleExoPlayer?= null
    private var mCurrentMilis : Long? = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exo_video_player)
    }

    private fun startPlayer(){
        mPlayer = ExoPlayerFactory.newSimpleInstance(this, DefaultRenderersFactory(this), DefaultTrackSelector(this))
        player.player = mPlayer
        player.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT

        val dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "player"))
        val extractorMediaSource = ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(
            Uri.parse(videoFile))

        val isResuming = mCurrentMilis !=0L
        mPlayer?.prepare(extractorMediaSource, isResuming, false)
        mPlayer?.playWhenReady = true
        if(isResuming){
            mPlayer?.seekTo(mCurrentMilis!!)
        }
    }

    private fun releasePlayer(){
        if(mPlayer == null){
            return
        }
        mCurrentMilis = mPlayer?.currentPosition
        mPlayer?.release()
        mPlayer = null
    }

    override fun onResume() {
        super.onResume()
        startPlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }
}

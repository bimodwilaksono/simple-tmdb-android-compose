package com.example.retrofitjetpackcompose.ui.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun MovieVideoScreen(navController: NavController, youtubeId: String) {
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    val activityLifecycle = lifecycleOwner.value.lifecycle
    val context = LocalContext.current

    val videoPlayer = remember {
        YouTubePlayerView(context).apply {
            activityLifecycle.addObserver(this)
            enableAutomaticInitialization = false
            initialize(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.apply {
                        loadOrCueVideo(activityLifecycle, youtubeId, 0f)
                        toggleFullScreen()
                    }
                }
            })
        }
    }
    AndroidView(
        {
            videoPlayer
        }, modifier = Modifier
            .fillMaxSize()
    )
}
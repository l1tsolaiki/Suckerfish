package com.l1tsolaiki.suckerfish

import android.media.MediaPlayer
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.l1tsolaiki.suckerfish.model.Song

class SongClickListener(private val activity: AppCompatActivity): RecyclerViewClickListener {

    private lateinit var player: MediaPlayer

    override fun onItemClicked(song: Song) {
        val songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            .buildUpon()
            .appendPath(song.resourceId.toString())
            .build()
        player = MediaPlayer.create(activity.baseContext, songUri)
        player.start()

        Log.i("Click listener", "On song clicked, name is $song")
    }
}
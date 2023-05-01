package com.l1tsolaiki.suckerfish

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.l1tsolaiki.suckerfish.adapter.Adapter
import com.l1tsolaiki.suckerfish.databinding.ActivityMusicBinding
import com.l1tsolaiki.suckerfish.model.Song
import com.l1tsolaiki.suckerfish.util.SongsReader
import com.l1tsolaiki.suckerfish.util.Temp


class MusicActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var binding: ActivityMusicBinding
    private val TAG = "MusicActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.songList.songList

        if (ensurePermissions()) {
            display()
        }
    }

    private fun display() : Unit {
        val songs = getSongs()
        Temp.readSong(baseContext, songs[0].resourceId)
        val app = this
        recyclerView.run {
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            adapter = Adapter(songs, SongClickListener(app))
        }
    }

    private fun getSongs() : List<Song> {
        return SongsReader.readSongs(applicationContext)
    }

    private fun ensurePermissions() : Boolean {
        Log.i(TAG, "Ensuring permission")
        if (baseContext.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission is already granted")
            return true
        } else {
            val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission())
            { granted: Boolean ->
                if (granted) {
                    display()
                }
            }
            launcher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        return false
    }
}
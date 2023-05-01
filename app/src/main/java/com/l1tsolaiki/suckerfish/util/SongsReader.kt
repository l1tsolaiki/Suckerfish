package com.l1tsolaiki.suckerfish.util

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import com.l1tsolaiki.suckerfish.model.Song

class SongsReader {
    companion object {
        fun readSongs(ctx: Context) : List<Song> {
            val songsList = mutableListOf<Song>()
            val collection = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.IS_MUSIC,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.ARTIST,
            )
            val query = ctx.contentResolver.query(
                collection,
                projection,
                MediaStore.Audio.Media.IS_MUSIC,
                null,
                null)
            query?.use { cursor ->
                val resourceIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
                val artistColum = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)

                Log.i("songsReader", "uri is $collection")
                Log.i("songsReader", "in songs reader")
                while (cursor.moveToNext()) {
                    val resourceId = cursor.getInt(resourceIdColumn)
                    val name = cursor.getString(nameColumn)
                    val artist = cursor.getString(artistColum)
                    songsList.add(Song(resourceId, name, artist))
                }
            }
            return songsList
        }
    }
}
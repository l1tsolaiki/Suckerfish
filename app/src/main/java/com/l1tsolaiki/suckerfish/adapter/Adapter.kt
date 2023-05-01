package com.l1tsolaiki.suckerfish.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.l1tsolaiki.suckerfish.model.Song
import com.l1tsolaiki.suckerfish.R
import com.l1tsolaiki.suckerfish.RecyclerViewClickListener

class Adapter(private val songs: List<Song>, private val listener: RecyclerViewClickListener) : RecyclerView.Adapter<Adapter.SongViewHolder>() {

    class SongViewHolder(itemView: View, clickAtPosition: (pos: Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val artist: TextView = itemView.findViewById(R.id.artist)

        init {
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val recyclerViewLayout: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_list_item, parent, false)
        return SongViewHolder(recyclerViewLayout) {
            listener.onItemClicked(songs[it])
        }
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.title.text = song.title
        holder.artist.text = song.artist
    }

    override fun getItemCount(): Int = songs.size
}
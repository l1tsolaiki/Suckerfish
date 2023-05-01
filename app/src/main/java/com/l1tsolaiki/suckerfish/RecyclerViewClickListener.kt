package com.l1tsolaiki.suckerfish

import com.l1tsolaiki.suckerfish.model.Song

interface RecyclerViewClickListener {
    fun onItemClicked(song: Song)
}
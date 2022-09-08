package com.tazarki.cleanArchitecture.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.tazarki.cleanArchitecture.R
import com.tazarki.cleanArchitecture.domain.model.Album

class AlbumItemAdapter(private val albums: List<Album>) : RecyclerView.Adapter<AlbumItemAdapter.AlbumHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder =
        AlbumHolder(LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false))

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) = holder.bindAlbum(albums[position])

    override fun getItemCount(): Int = albums.size


    class AlbumHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindAlbum(album: Album) {
            itemView.findViewById<ImageView>(R.id.album_item_image_iv).load(album.thumbnail){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            itemView.findViewById<TextView>(R.id.album_item_title_tv).text = album.title
            itemView.findViewById<TextView>(R.id.album_item_des_tv).text = album.description
        }

    }
}
package com.tazarki.cleanArchitecture.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tazarki.cleanArchitecture.domain.model.Album

@Entity
data class AlbumEntity (
    val description: String,
    val thumbnail: String,
    val title: String,
    @PrimaryKey val ref: Int,
){
    fun toAlbum(): Album{
        return Album(
            description = description,
            thumbnail = thumbnail,
            title = title,
            ref = ref
        )
    }
}
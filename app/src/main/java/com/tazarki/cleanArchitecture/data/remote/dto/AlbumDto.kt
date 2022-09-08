package com.tazarki.cleanArchitecture.data.remote.dto

import com.tazarki.cleanArchitecture.data.local.AlbumEntity

data class AlbumDto(
    val description: String,
    val ref: Int,
    val thumbnail: String,
    val title: String,
    val url: String
)

fun AlbumDto.toAlbumEntity(): AlbumEntity{
    return AlbumEntity(
        description = description,
        ref = ref,
        thumbnail = thumbnail,
        title = title
    )
}



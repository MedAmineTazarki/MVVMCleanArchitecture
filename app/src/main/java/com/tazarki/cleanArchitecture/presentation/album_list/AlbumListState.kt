package com.tazarki.cleanArchitecture.presentation.album_list

import com.tazarki.cleanArchitecture.domain.model.Album

data class AlbumListState(
    val isLoading: Boolean = false,
    val albums: List<Album> = emptyList(),
    val error: String = ""
)

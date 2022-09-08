package com.tazarki.cleanArchitecture.domain.repository

import com.tazarki.cleanArchitecture.common.Resource
import com.tazarki.cleanArchitecture.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {

    fun getAlbums(): Flow<Resource<List<Album>>>
}
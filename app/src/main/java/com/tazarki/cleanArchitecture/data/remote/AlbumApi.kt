package com.tazarki.cleanArchitecture.data.remote

import com.tazarki.cleanArchitecture.data.remote.dto.AlbumDto
import retrofit2.http.GET

interface AlbumApi {

    @GET("bigburger")
    suspend fun getAlbums(): List<AlbumDto>
}
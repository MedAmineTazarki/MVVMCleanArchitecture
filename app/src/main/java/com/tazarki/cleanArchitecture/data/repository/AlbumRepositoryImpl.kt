package com.tazarki.cleanArchitecture.data.repository

import com.tazarki.cleanArchitecture.common.Resource
import com.tazarki.cleanArchitecture.data.local.AlbumDao
import com.tazarki.cleanArchitecture.data.remote.AlbumApi
import com.tazarki.cleanArchitecture.data.remote.dto.toAlbumEntity
import com.tazarki.cleanArchitecture.domain.model.Album
import com.tazarki.cleanArchitecture.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val api: AlbumApi,
    private val dao: AlbumDao
) : AlbumRepository{

    override fun getAlbums(): Flow<Resource<List<Album>>> = flow {
        emit(Resource.Loading())
        val albums = dao.getAlbums().map { it.toAlbum() }
        emit(Resource.Loading(data = albums))
        try {
            val remoteAlbums = api.getAlbums()
            dao.deleteAlbum(remoteAlbums.map { it.ref })
            dao.insertAlbum(remoteAlbums.map { it.toAlbumEntity() })
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured", data = albums))
        } catch (e: IOException){
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection", data = albums))
        }

        val albumsRefreshed = dao.getAlbums().map { it.toAlbum() }
        emit(Resource.Success(albumsRefreshed))
    }
}
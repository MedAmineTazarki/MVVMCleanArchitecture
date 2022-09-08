package com.tazarki.cleanArchitecture.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(albums: List<AlbumEntity>)

    @Query("DELETE FROM albumentity WHERE ref IN(:albumsId)")
    suspend fun deleteAlbum(albumsId: List<Int>)

    @Query("SELECT * FROM albumentity")
    suspend fun getAlbums() : List<AlbumEntity>
}
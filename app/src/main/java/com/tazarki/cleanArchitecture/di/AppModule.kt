package com.tazarki.cleanArchitecture.di

import android.app.Application
import androidx.room.Room
import com.tazarki.cleanArchitecture.common.Constant
import com.tazarki.cleanArchitecture.data.local.AlbumDatabase
import com.tazarki.cleanArchitecture.data.remote.AlbumApi
import com.tazarki.cleanArchitecture.data.repository.AlbumRepositoryImpl
import com.tazarki.cleanArchitecture.domain.repository.AlbumRepository
import com.tazarki.cleanArchitecture.domain.use_case.get_albums.GetAlbumsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAlbumApi() : AlbumApi{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlbumApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAlbumDatabase(application: Application) : AlbumDatabase{
        return Room.databaseBuilder(application, AlbumDatabase::class.java, "album_db").build()
    }

    @Provides
    @Singleton
    fun provideAlbumRepository(api: AlbumApi, db: AlbumDatabase): AlbumRepository{
        return AlbumRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideAlbums(repository: AlbumRepository): GetAlbumsUseCase{
        return GetAlbumsUseCase(repository)
    }
}
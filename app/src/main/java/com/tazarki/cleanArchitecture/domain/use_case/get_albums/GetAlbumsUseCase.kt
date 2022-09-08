package com.tazarki.cleanArchitecture.domain.use_case.get_albums

import com.tazarki.cleanArchitecture.common.Resource
import com.tazarki.cleanArchitecture.domain.model.Album
import com.tazarki.cleanArchitecture.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val repository: AlbumRepository
) {
    operator fun invoke(): Flow<Resource<List<Album>>> {
        return repository.getAlbums()
    }
}
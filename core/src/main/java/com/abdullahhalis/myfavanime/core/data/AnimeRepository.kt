package com.abdullahhalis.myfavanime.core.data

import android.util.Log
import com.abdullahhalis.myfavanime.core.data.source.local.LocalDataSource
import com.abdullahhalis.myfavanime.core.data.source.remote.RemoteDataSource
import com.abdullahhalis.myfavanime.core.data.source.remote.network.ApiResponse
import com.abdullahhalis.myfavanime.core.domain.model.Anime
import com.abdullahhalis.myfavanime.core.domain.model.DetailAnime
import com.abdullahhalis.myfavanime.core.domain.repository.IAnimeRepository
import com.abdullahhalis.myfavanime.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class AnimeRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IAnimeRepository {
    override fun getTopAnime(): Flow<Resource<List<Anime>>> = flow {
        emit(Resource.Loading())
        try {
            when(val response = remoteDataSource.getTopAnime().first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(DataMapper.mapResponseToDomain(response.data)))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Error("There is no data"))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(response.errorMessage))
                }
            }
        } catch (e: Exception) {
            Log.e(AnimeRepository::class.java.simpleName, e.message.toString())
            emit(Resource.Error(e.message.toString()))
        }
    }

    override fun getSearchedAnime(q: String): Flow<Resource<List<Anime>>> = flow {
        emit(Resource.Loading())
        try {
            when (val response = remoteDataSource.getSearchedAnime(q).first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(DataMapper.mapResponseToDomain(response.data)))
                }

                is ApiResponse.Empty -> {
                    emit(Resource.Error("There is no data"))
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error(response.errorMessage))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
            Log.e(AnimeRepository::class.java.simpleName, e.message.toString())
        }
    }

    override fun getDetailAnime(id: Int): Flow<Resource<DetailAnime>> = flow {
        emit(Resource.Loading())
        try {
            when (val response = remoteDataSource.getDetailAnime(id).first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(DataMapper.mapDetailAnimeResponseToDomain(response.data)))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(response.errorMessage))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Error("There is no data"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
            Log.e(AnimeRepository::class.java.simpleName, e.message.toString())
        }
    }

    override fun getAllFavoriteAnime(): Flow<List<Anime>> = localDataSource.getAllFavoriteAnime().map { DataMapper.mapEntitiesToDomain(it) }

    override suspend fun addFavoriteAnime(anime: Anime) {
        val animeEntity = DataMapper.mapDomainToEntities(anime)
        localDataSource.addFavoriteAnime(animeEntity)
    }

    override suspend fun deleteFavoriteAnimeById(id: Int) {
        localDataSource.deleteFavoriteAnimeById(id)
    }

    override fun isAnimeFavorite(id: Int): Flow<Boolean> = localDataSource.isAnimeFavorite(id)
}
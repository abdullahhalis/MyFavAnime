package com.abdullahhalis.myfavanime.core.data.source.local

import com.abdullahhalis.myfavanime.core.data.source.local.entity.FavoriteAnimeEntity
import com.abdullahhalis.myfavanime.core.data.source.local.room.FavAnimeDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val favAnimeDao: FavAnimeDao) {
    fun getAllFavoriteAnime(): Flow<List<FavoriteAnimeEntity>> = favAnimeDao.getAllFavoriteAnime()

    suspend fun addFavoriteAnime(favoriteAnimeEntity: FavoriteAnimeEntity) = favAnimeDao.addFavorite(favoriteAnimeEntity)

    suspend fun deleteFavoriteAnimeById(id: Int) = favAnimeDao.deleteFavoriteById(id)

    fun isAnimeFavorite(id: Int): Flow<Boolean> = favAnimeDao.isAnimeFavorite(id)
}
package com.abdullahhalis.myfavanime.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abdullahhalis.myfavanime.core.data.source.local.entity.FavoriteAnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavAnimeDao{
    @Query("SELECT * FROM FavoriteAnimeEntity")
    fun getAllFavoriteAnime(): Flow<List<FavoriteAnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favoriteAnimeEntity: FavoriteAnimeEntity)

    @Query("DELETE FROM FavoriteAnimeEntity WHERE id = :id ")
    suspend fun deleteFavoriteById(id: Int)

    @Query("SELECT EXISTS(SELECT * FROM favoriteanimeentity WHERE id = :id)")
    fun isAnimeFavorite(id: Int): Flow<Boolean>
}
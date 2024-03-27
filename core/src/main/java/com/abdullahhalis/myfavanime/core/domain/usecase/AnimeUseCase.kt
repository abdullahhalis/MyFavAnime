package com.abdullahhalis.myfavanime.core.domain.usecase

import com.abdullahhalis.myfavanime.core.data.Resource
import com.abdullahhalis.myfavanime.core.domain.model.Anime
import com.abdullahhalis.myfavanime.core.domain.model.DetailAnime
import kotlinx.coroutines.flow.Flow

interface AnimeUseCase {
    fun getTopAnime(): Flow<Resource<List<Anime>>>
    fun getSearchedAnime(q: String): Flow<Resource<List<Anime>>>
    fun getDetailAnime(id: Int): Flow<Resource<DetailAnime>>
    fun getAllFavoriteAnime(): Flow<List<Anime>>
    suspend fun addFavoriteAnime(anime: Anime)
    suspend fun deleteFavoriteAnimeById(id: Int)
    fun isAnimeFavorite(id: Int): Flow<Boolean>
}
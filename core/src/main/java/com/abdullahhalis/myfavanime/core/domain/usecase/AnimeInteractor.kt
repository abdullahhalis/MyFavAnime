package com.abdullahhalis.myfavanime.core.domain.usecase

import com.abdullahhalis.myfavanime.core.data.Resource
import com.abdullahhalis.myfavanime.core.domain.model.Anime
import com.abdullahhalis.myfavanime.core.domain.model.DetailAnime
import com.abdullahhalis.myfavanime.core.domain.repository.IAnimeRepository
import kotlinx.coroutines.flow.Flow

class AnimeInteractor(private val animeRepository: IAnimeRepository): AnimeUseCase {
    override fun getTopAnime(): Flow<Resource<List<Anime>>> = animeRepository.getTopAnime()

    override fun getSearchedAnime(q: String): Flow<Resource<List<Anime>>> = animeRepository.getSearchedAnime(q)

    override fun getDetailAnime(id: Int): Flow<Resource<DetailAnime>> = animeRepository.getDetailAnime(id)

    override fun getAllFavoriteAnime(): Flow<List<Anime>> = animeRepository.getAllFavoriteAnime()

    override suspend fun addFavoriteAnime(anime: Anime) = animeRepository.addFavoriteAnime(anime)

    override suspend fun deleteFavoriteAnimeById(id: Int) = animeRepository.deleteFavoriteAnimeById(id)

    override fun isAnimeFavorite(id: Int): Flow<Boolean> = animeRepository.isAnimeFavorite(id)
}
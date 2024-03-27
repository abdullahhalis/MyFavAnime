package com.abdullahhalis.myfavanime.core.utils

import com.abdullahhalis.myfavanime.core.data.source.local.entity.FavoriteAnimeEntity
import com.abdullahhalis.myfavanime.core.data.source.remote.response.AnimeResponse
import com.abdullahhalis.myfavanime.core.data.source.remote.response.DetailAnimeResponse
import com.abdullahhalis.myfavanime.core.data.source.remote.response.GenresItem
import com.abdullahhalis.myfavanime.core.domain.model.Anime
import com.abdullahhalis.myfavanime.core.domain.model.DetailAnime
import com.abdullahhalis.myfavanime.core.domain.model.Genres

object DataMapper {
    fun mapResponseToDomain(input: List<AnimeResponse>): List<Anime> = input.map {
        Anime(
            id = it.node.id,
            title = it.node.title,
            pictureUrl = it.node.mainPicture.large,
            score = it.node.mean,
            mediaType = it.node.mediaType,
            numEpisodes = it.node.numEpisodes
        )
    }

    fun mapEntitiesToDomain(input: List<FavoriteAnimeEntity>): List<Anime> = input.map {
        Anime(
            id = it.id,
            title = it.title,
            pictureUrl = it.pictureUrl,
            score = it.score,
            mediaType = it.mediaType,
            numEpisodes = it.numEpisodes
        )
    }

    fun mapDomainToEntities(input: Anime) = FavoriteAnimeEntity(
        id = input.id,
        title = input.title,
        pictureUrl = input.pictureUrl,
        score = input.score,
        mediaType = input.mediaType,
        numEpisodes = input.numEpisodes
    )

    fun mapDetailAnimeResponseToDomain(input: DetailAnimeResponse) = DetailAnime(
        id = input.id,
        title = input.title,
        pictureUrl = input.mainPicture.large,
        synopsis = input.synopsis,
        mediaType = input.mediaType ?: "?",
        status = input.status,
        startDate = input.startDate ?: "?",
        score = input.mean ?: 0f,
        numScoringUsers = input.numScoringUsers ?: 0,
        numEpisodes = input.numEpisodes,
        episodeDuration = input.averageEpisodeDuration,
        listGenres = input.genres?.map { mapGenreResponseToDomain(it) } ?: emptyList()
    )

    private fun mapGenreResponseToDomain(genresItem: GenresItem) = Genres(
        id = genresItem.id,
        name = genresItem.name
    )
}
package com.abdullahhalis.myfavanime.core.domain.model

data class DetailAnime(
    val id: Int,
    val title: String,
    val pictureUrl: String,
    val startDate: String,
    val synopsis: String,
    val score: Float,
    val numScoringUsers: Int,
    val mediaType: String,
    val status: String,
    val numEpisodes: Int,
    val episodeDuration: Int,
    val listGenres: List<Genres>
)

data class Genres(
    val id: Int,
    val name: String
)

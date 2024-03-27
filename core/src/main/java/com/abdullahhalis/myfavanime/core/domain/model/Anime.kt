package com.abdullahhalis.myfavanime.core.domain.model

data class Anime(
    val id: Int,
    val title: String,
    val pictureUrl: String,
    val score: Float,
    val mediaType: String,
    val numEpisodes: Int
)

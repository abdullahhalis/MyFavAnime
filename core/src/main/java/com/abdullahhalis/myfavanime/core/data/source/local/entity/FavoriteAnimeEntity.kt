package com.abdullahhalis.myfavanime.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteAnimeEntity")
data class FavoriteAnimeEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "picture_url")
    val pictureUrl: String,

    @ColumnInfo(name = "score")
    val score: Float,

    @ColumnInfo(name = "media_type")
    val mediaType: String,

    @ColumnInfo(name = "num_episodes")
    val numEpisodes: Int
)
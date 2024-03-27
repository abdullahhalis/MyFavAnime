package com.abdullahhalis.myfavanime.core.data.source.remote.network

import com.abdullahhalis.myfavanime.core.data.source.remote.response.DetailAnimeResponse
import com.abdullahhalis.myfavanime.core.data.source.remote.response.ListAnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("anime/ranking")
    suspend fun getTopAnime(
        @Query("ranking_type") rankingType: String = "all",
        @Query("limit") limit: Int = 50,
        @Query("fields") fields: String = "id,title,main_picture,mean,rank,media_type,num_episodes",
        @Query("offset") offset: Int = 0
    ): ListAnimeResponse

    @GET("anime")
    suspend fun getSearchedAnime(
        @Query("q") q: String,
        @Query("fields") fields: String = "id,title,main_picture,mean,rank,media_type,num_episodes",
        @Query("offset") offset: Int = 0
    ): ListAnimeResponse

    @GET("anime/{anime_id}")
    suspend fun getDetailAnime(
        @Path("anime_id") animeId: Int,
        @Query("fields") fields: String = "id,title,main_picture,start_date,synopsis,mean,rank,num_scoring_users,media_type,status,genres,num_episodes,average_episode_duration"
    ): DetailAnimeResponse
}
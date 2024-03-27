package com.abdullahhalis.myfavanime.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailAnimeResponse(

	@field:SerializedName("main_picture")
	val mainPicture: MainPicture,

	@field:SerializedName("synopsis")
	val synopsis: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("average_episode_duration")
	val averageEpisodeDuration: Int,

	@field:SerializedName("media_type")
	val mediaType: String? = null,

	@field:SerializedName("mean")
	val mean: Float? = null,

	@field:SerializedName("genres")
	val genres: List<GenresItem>? = null,

	@field:SerializedName("rank")
	val rank: Int? = null,

	@field:SerializedName("num_scoring_users")
	val numScoringUsers: Int? = null,

	@field:SerializedName("num_episodes")
	val numEpisodes: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("start_date")
	val startDate: String? = null,

	@field:SerializedName("status")
	val status: String
)
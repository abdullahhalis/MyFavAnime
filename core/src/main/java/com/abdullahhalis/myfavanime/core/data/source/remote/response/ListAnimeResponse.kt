package com.abdullahhalis.myfavanime.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListAnimeResponse(

	@field:SerializedName("data")
	val data: List<AnimeResponse>,

	@field:SerializedName("paging")
	val paging: Paging
)

data class Paging(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("previous")
	val previous: String
)

data class AnimeResponse(

	@field:SerializedName("node")
	val node: Node,
)

data class Node(

	@field:SerializedName("media_type")
	val mediaType: String,

	@field:SerializedName("mean")
	val mean: Float,

	@field:SerializedName("rank")
	val rank: Int,

	@field:SerializedName("main_picture")
	val mainPicture: MainPicture,

	@field:SerializedName("num_episodes")
	val numEpisodes: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String
)

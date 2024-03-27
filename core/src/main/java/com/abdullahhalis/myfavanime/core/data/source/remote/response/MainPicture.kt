package com.abdullahhalis.myfavanime.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MainPicture(

	@field:SerializedName("large")
	val large: String,

	@field:SerializedName("medium")
	val medium: String
)
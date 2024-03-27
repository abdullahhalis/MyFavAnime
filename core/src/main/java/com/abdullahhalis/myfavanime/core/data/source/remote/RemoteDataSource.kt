package com.abdullahhalis.myfavanime.core.data.source.remote

import android.util.Log
import com.abdullahhalis.myfavanime.core.data.source.remote.network.ApiResponse
import com.abdullahhalis.myfavanime.core.data.source.remote.network.ApiService
import com.abdullahhalis.myfavanime.core.data.source.remote.response.AnimeResponse
import com.abdullahhalis.myfavanime.core.data.source.remote.response.DetailAnimeResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class RemoteDataSource(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun getTopAnime(): Flow<ApiResponse<List<AnimeResponse>>> = flow {
        try {
            val response = apiService.getTopAnime()
            val dataArray = response.data
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(dataArray))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
            Log.e(RemoteDataSource::class.java.simpleName, e.toString())
        }
    }.flowOn(ioDispatcher)

    fun getSearchedAnime(q: String): Flow<ApiResponse<List<AnimeResponse>>> = flow {
        try {
            val response = apiService.getSearchedAnime(q)
            val dataArray = response.data
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(dataArray))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: HttpException) {
            if (e.code() == 400) {
                emit(ApiResponse.Error("Bad Request, please input more than 2 characters to search anime"))
            } else {
                emit(ApiResponse.Error(e.message.toString()))
            }
            Log.e(RemoteDataSource::class.java.simpleName, e.toString())
        }
    }.flowOn(ioDispatcher)

    fun getDetailAnime(id: Int): Flow<ApiResponse<DetailAnimeResponse>> = flow {
        try {
            val response = apiService.getDetailAnime(id)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
            Log.e(RemoteDataSource::class.java.simpleName, e.toString())
        }
    }.flowOn(ioDispatcher)
}
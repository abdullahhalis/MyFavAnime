package com.abdullahhalis.myfavanime.favorite.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdullahhalis.myfavanime.core.data.Resource
import com.abdullahhalis.myfavanime.core.domain.model.Anime
import com.abdullahhalis.myfavanime.core.domain.usecase.AnimeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(private val animeUseCase: AnimeUseCase) : ViewModel() {
    private val _favoriteAnime: MutableStateFlow<Resource<List<Anime>>> =
        MutableStateFlow(Resource.Loading())
    val favoriteAnime: StateFlow<Resource<List<Anime>>> get() = _favoriteAnime

    fun getAllFavoriteAnime() {
        viewModelScope.launch {
            animeUseCase.getAllFavoriteAnime()
                .catch {
                    _favoriteAnime.value = Resource.Error(it.message.toString())
                }
                .collect { favoriteAnime ->
                    _favoriteAnime.value = Resource.Success(favoriteAnime)
                }
        }
    }
}
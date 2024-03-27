package com.abdullahhalis.myfavanime.ui.screen.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.abdullahhalis.myfavanime.core.data.Resource
import com.abdullahhalis.myfavanime.core.domain.model.Anime
import com.abdullahhalis.myfavanime.core.domain.model.DetailAnime
import com.abdullahhalis.myfavanime.core.domain.usecase.AnimeUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class DetailAnimeViewModel(private val animeUseCase: AnimeUseCase) : ViewModel() {
    private val _detailAnime: MutableStateFlow<Resource<DetailAnime>> =
        MutableStateFlow(Resource.Loading())
    val detailAnime: StateFlow<Resource<DetailAnime>> get() = _detailAnime

    @OptIn(FlowPreview::class)
    fun getDetailAnime(id: Int) {
        viewModelScope.launch {
            animeUseCase.getDetailAnime(id)
                .debounce(1000)
                .catch {
                    _detailAnime.value = Resource.Error(it.message.toString())
                }
                .collect { anime ->
                    _detailAnime.value = anime
                }
        }
    }

    private val favoriteAnime = MutableLiveData<Anime>()

    fun setFavoriteAnime(anime: Anime) {
        favoriteAnime.value = anime
    }

    val favoriteStatus = favoriteAnime
        .switchMap {
            animeUseCase.isAnimeFavorite(it.id).asLiveData()
        }

    fun changeFavorite(anime: Anime) {
        viewModelScope.launch {
            if (favoriteStatus.value as Boolean) {
                animeUseCase.deleteFavoriteAnimeById(anime.id)
            } else {
                animeUseCase.addFavoriteAnime(anime)
            }
        }
    }
}
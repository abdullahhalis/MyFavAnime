package com.abdullahhalis.myfavanime.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.abdullahhalis.myfavanime.core.domain.usecase.AnimeUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach

class HomeViewModel(private val animeUseCase: AnimeUseCase) : ViewModel() {
    val topAnime = animeUseCase.getTopAnime()

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    private val _expanded = mutableStateOf(false)
    val expanded: State<Boolean> get() = _expanded

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }

    fun setExpanded(expanded: Boolean) {
        _expanded.value = expanded
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchResult = _query
        .debounce(500)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest {
            animeUseCase.getSearchedAnime(it)
        }
        .onEach { setExpanded(true) }
}
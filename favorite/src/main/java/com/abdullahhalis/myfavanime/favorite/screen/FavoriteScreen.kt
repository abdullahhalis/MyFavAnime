package com.abdullahhalis.myfavanime.favorite.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.abdullahhalis.myfavanime.core.data.Resource
import com.abdullahhalis.myfavanime.core.domain.model.Anime
import com.abdullahhalis.myfavanime.ui.components.ErrorItem
import com.abdullahhalis.myfavanime.ui.components.GridAnimeList
import com.abdullahhalis.myfavanime.ui.components.LoadingItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    favoriteViewModel: FavoriteViewModel = koinViewModel()
) {
    favoriteViewModel.favoriteAnime.collectAsState(initial = Resource.Loading()).value.let { state ->
        when (state) {
            is Resource.Loading -> {
                LoadingItem(modifier)
                favoriteViewModel.getAllFavoriteAnime()
            }

            is Resource.Success -> {
                state.data?.let { FavoriteContent(listAnime = it, navigateToDetail = navigateToDetail, modifier = modifier) }
            }

            is Resource.Error -> {
                ErrorItem(state.message.toString(), modifier = modifier)
            }
        }
    }
}

@Composable
fun FavoriteContent(
    listAnime: List<Anime>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (listAnime.isNotEmpty()) {
        GridAnimeList(listAnime = listAnime, navigateToDetail = navigateToDetail, modifier = modifier)
    } else {
        ErrorItem("There is no data", modifier = modifier)
    }
}
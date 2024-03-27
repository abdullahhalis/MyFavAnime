package com.abdullahhalis.myfavanime.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abdullahhalis.myfavanime.core.domain.model.Anime

@Composable
fun GridAnimeList(
    listAnime: List<Anime>?,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        listAnime?.let {
            items(listAnime) { data ->
                AnimeGridItem(
                    id = data.id,
                    title = data.title,
                    pictureUrl = data.pictureUrl,
                    score = data.score,
                    mediaType = data.mediaType,
                    numEpisodes = data.numEpisodes,
                    navigateToDetail = navigateToDetail
                )
            }
        }
    }
}
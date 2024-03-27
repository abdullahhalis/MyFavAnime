package com.abdullahhalis.myfavanime.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.abdullahhalis.myfavanime.R
import com.abdullahhalis.myfavanime.core.data.Resource
import com.abdullahhalis.myfavanime.core.domain.model.Anime
import com.abdullahhalis.myfavanime.core.domain.model.DetailAnime
import com.abdullahhalis.myfavanime.ui.components.ErrorItem
import com.abdullahhalis.myfavanime.ui.components.GenreItem
import com.abdullahhalis.myfavanime.ui.components.LoadingItem
import com.abdullahhalis.myfavanime.utils.formatNumber
import com.abdullahhalis.myfavanime.utils.mediaTypes
import com.abdullahhalis.myfavanime.utils.secondToMinutes
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailAnimeScreen(
    id: Int,
    modifier: Modifier = Modifier,
    detailAnimeViewModel: DetailAnimeViewModel = koinViewModel()
) {
    detailAnimeViewModel.detailAnime.collectAsState(initial = Resource.Loading()).value.let { state ->
        when (state) {
            is Resource.Loading -> {
                LoadingItem()
                detailAnimeViewModel.getDetailAnime(id)
            }

            is Resource.Success -> {
                state.data?.let {
                    val anime = Anime(
                        id = it.id,
                        title = it.title,
                        score = it.score,
                        numEpisodes = it.numEpisodes,
                        mediaType = it.mediaType,
                        pictureUrl = it.pictureUrl
                    )
                    detailAnimeViewModel.setFavoriteAnime(anime)
                    val favoriteStatus by detailAnimeViewModel.favoriteStatus.observeAsState(initial = false)
                    DetailAnimeContent(
                        detailAnime = it,
                        favoriteStatus = favoriteStatus,
                        updateFavoriteStatus = {
                            detailAnimeViewModel.changeFavorite(anime)
                        },
                        modifier = modifier
                    )
                }
            }

            is Resource.Error -> {
                ErrorItem(message = state.message.toString())
            }
        }
    }
}

@Composable
fun DetailAnimeContent(
    detailAnime: DetailAnime,
    favoriteStatus: Boolean,
    updateFavoriteStatus: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = updateFavoriteStatus,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color(0xFFF72798),
            ) {
                Icon(
                    imageVector = if (favoriteStatus) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = stringResource(R.string.title_favorite),
                )
            }
        }
    ) { contentPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = detailAnime.pictureUrl,
                contentDescription = stringResource(R.string.picture),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 250.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = (-55).dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
                    ) {
                        AsyncImage(
                            model = detailAnime.pictureUrl,
                            contentDescription = stringResource(R.string.picture),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .sizeIn(maxWidth = 160.dp, maxHeight = 200.dp)
                                .padding(16.dp)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
                            .padding(top = 16.dp, end = 16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.score), style = MaterialTheme.typography.titleSmall.copy(
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.star_outline),
                                contentDescription = stringResource(R.string.score),
                                tint = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier
                                    .padding(start = 2.dp, bottom = 2.dp)
                                    .size(16.dp)
                            )
                            Text(
                                text = detailAnime.score.toString(),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onBackground,
                                )
                            )
                            Text(
                                text = stringResource(
                                    R.string.num_votes,
                                    detailAnime.numScoringUsers.formatNumber()
                                ),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onBackground,
                                )
                            )
                        }
                        Text(
                            text = stringResource(R.string.mediatype), style = MaterialTheme.typography.titleSmall.copy(
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = detailAnime.mediaType.mediaTypes(),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            )
                            if (detailAnime.numEpisodes > 1) {
                                Text(
                                    text = stringResource(
                                        R.string.num_eps_detail,
                                        detailAnime.numEpisodes
                                    ),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = MaterialTheme.colorScheme.onBackground
                                    )
                                )
                            }
                            Text(
                                text = stringResource(
                                    R.string.duration_in_minutes,
                                    detailAnime.episodeDuration.secondToMinutes()
                                ),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            )
                        }
                        Text(
                            text = stringResource(R.string.status), style = MaterialTheme.typography.titleSmall.copy(
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                        Text(
                            text = detailAnime.status.mediaTypes(),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                        Text(
                            text = stringResource(R.string.release_date), style = MaterialTheme.typography.titleSmall.copy(
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                        Text(
                            text = detailAnime.startDate,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    }
                }
                Text(
                    text = detailAnime.title, style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ), modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(80.dp),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    userScrollEnabled = false,
                    modifier = Modifier.heightIn(max = 150.dp)
                ) {
                    items(detailAnime.listGenres) { genre ->
                        GenreItem(name = genre.name)
                    }
                }
                Text(
                    text = detailAnime.synopsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}

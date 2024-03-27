package com.abdullahhalis.myfavanime.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.abdullahhalis.myfavanime.R
import com.abdullahhalis.myfavanime.core.data.Resource
import com.abdullahhalis.myfavanime.core.domain.model.Anime
import com.abdullahhalis.myfavanime.ui.components.AnimeListItem
import com.abdullahhalis.myfavanime.ui.components.ErrorItem
import com.abdullahhalis.myfavanime.ui.components.GridAnimeList
import com.abdullahhalis.myfavanime.ui.components.LoadingItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val query = homeViewModel.query.collectAsState().value
    val expanded = homeViewModel.expanded.value
    var isSearch by remember {
        mutableStateOf(false)
    }

    val topAnime = homeViewModel.topAnime.collectAsState(initial = Resource.Loading()).value
    val searchedAnime = homeViewModel.searchResult.collectAsState(initial = null).value

    Column(
        modifier = modifier
    ) {
        MySearchBar(
            query = query,
            listAnime = searchedAnime,
            isActive = expanded,
            onQueryChange = homeViewModel::onQueryChange,
            onActiveChange = { homeViewModel.setExpanded(false) },
            navigateToDetail = navigateToDetail,
            onSearch = {
                isSearch = true
                homeViewModel.setExpanded(false)
            }
        )
        if (isSearch) {
            searchedAnime?.let { HomeContent(listAnime = it, navigateToDetail = navigateToDetail) }
        } else {
            HomeContent(
                listAnime = topAnime,
                navigateToDetail = navigateToDetail
            )
        }
    }
}

@Composable
fun HomeContent(
    listAnime: Resource<List<Anime>>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    when (listAnime) {
        is Resource.Loading -> {
            LoadingItem(modifier)
        }

        is Resource.Success -> {
            GridAnimeList(listAnime = listAnime.data, navigateToDetail = navigateToDetail, modifier = modifier)
        }

        is Resource.Error -> {
            ErrorItem(message = listAnime.message.toString(), modifier = modifier)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    query: String,
    listAnime: Resource<List<Anime>>?,
    isActive: Boolean,
    onQueryChange: (String) -> Unit,
    navigateToDetail: (Int) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = isActive,
        onActiveChange = onActiveChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search_anime),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text(text = stringResource(R.string.search_anime), color = MaterialTheme.colorScheme.onSurfaceVariant)
        },
        shape = MaterialTheme.shapes.small,
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            inputFieldColors = TextFieldDefaults.colors(
                Color.Black
            )
        ),
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp, max = 360.dp),
        content = {
            when (listAnime) {
                is Resource.Loading -> LoadingItem()
                is Resource.Success -> {
                    listAnime.data?.let { listAnime ->
                        LazyColumn {
                            items(listAnime) { anime ->
                                AnimeListItem(
                                    id = anime.id,
                                    title = anime.title,
                                    pictureUrl = anime.pictureUrl,
                                    score = anime.score,
                                    mediaType = anime.mediaType,
                                    numEpisodes = anime.numEpisodes,
                                    navigateToDetail = navigateToDetail
                                )
                            }
                        }
                    }
                }
                is Resource.Error -> {
                    ErrorItem(message = listAnime.message.toString())
                }
                else -> {
                    ErrorItem(message = listAnime?.message.toString())
                }
            }
        }
    )
}
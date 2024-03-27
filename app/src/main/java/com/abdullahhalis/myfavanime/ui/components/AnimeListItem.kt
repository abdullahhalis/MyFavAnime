package com.abdullahhalis.myfavanime.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.abdullahhalis.myfavanime.R
import com.abdullahhalis.myfavanime.utils.mediaTypes

@Composable
fun AnimeListItem(
    id: Int,
    title: String,
    pictureUrl: String,
    score: Float,
    mediaType: String,
    numEpisodes: Int,
    navigateToDetail: (Int) -> Unit,
) {
    Card(
        shape = CardDefaults.outlinedShape,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 60.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { navigateToDetail(id) }
    ) {
        Row {
            AsyncImage(
                model = pictureUrl,
                contentDescription = stringResource(R.string.picture),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(60.dp)
            )
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W700
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = mediaType.mediaTypes(),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.Black,
                            fontSize = 10.sp
                        )
                    )
                    if (numEpisodes > 1) {
                        Text(
                            text = stringResource(R.string.num_episodes, numEpisodes),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.Black,
                                fontSize = 10.sp
                            )
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.star_outline),
                        contentDescription = stringResource(R.string.score),
                        tint = Color.Black,
                        modifier = Modifier
                            .padding(start = 2.dp, bottom = 2.dp)
                            .size(14.dp)
                    )
                    Text(
                        text = score.toString(),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.Black,
                            fontSize = 10.sp
                        )
                    )
                }
            }
        }
    }
}
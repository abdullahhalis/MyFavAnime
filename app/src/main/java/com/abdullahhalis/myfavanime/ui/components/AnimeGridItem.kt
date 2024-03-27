package com.abdullahhalis.myfavanime.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
fun AnimeGridItem(
    id: Int,
    title: String,
    pictureUrl: String,
    score: Float,
    mediaType: String,
    numEpisodes: Int,
    navigateToDetail: (Int) -> Unit,
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = Modifier
            .width(200.dp)
            .clickable { navigateToDetail(id) }
    ) {
        AsyncImage(
            model = pictureUrl,
            contentDescription = stringResource(R.string.picture),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(250.dp)
        )
        Box(
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.5f))
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.White,
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
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    )
                    if (numEpisodes > 1) {
                        Text(
                            text = stringResource(R.string.num_episodes, numEpisodes),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.White,
                                fontSize = 10.sp
                            )
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.star_outline),
                        contentDescription = stringResource(R.string.score),
                        tint = Color.White,
                        modifier = Modifier
                            .padding(start = 2.dp, bottom = 2.dp)
                            .size(14.dp)
                    )
                    Text(
                        text = score.toString(),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    )
                }
            }
        }
    }
}
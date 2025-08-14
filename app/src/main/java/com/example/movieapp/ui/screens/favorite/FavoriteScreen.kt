package com.example.movieapp.ui.screens.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.data.db.FavoriteEntity
import com.example.movieapp.data.models.detail.ResponseDetail
import com.example.movieapp.ui.theme.CoolYellow
import com.example.movieapp.ui.theme.coolWhite
import com.example.movieapp.ui.theme.philippineSilver
import com.example.movieapp.ui.theme.scarlet
import com.example.movieapp.utils.Constants.BASE_IMAGE
import com.example.movieapp.utils.screens.MyScreens

@Composable
fun FavoriteScreen(navController: NavController) {
    val viewModel= hiltViewModel<FavoriteViewModel>()
    Column {
       FavoriteMovies(favoriteList =viewModel.getAllFavorite() ) {
           navController.navigate(MyScreens.DetailScreen.route+"/$it")
       }
    }
}

@Composable
fun FavoriteMovies(
    modifier: Modifier = Modifier,
    favoriteList: List<FavoriteEntity>,
    onItemClicked: (Int) -> Unit
) {
    val context = LocalContext.current
    Text(
        text = "Favorite Movies",
        color = CoolYellow,
        modifier = Modifier.padding(start = 8.dp, top = 16.dp)
    )
    LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {

        items(favoriteList) { movie ->
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)
                    .clickable { onItemClicked(movie.favorite?.id!!) }
            ) {

                Row(modifier = Modifier.padding(start = 8.dp, top = 8.dp)) {
                    Card(
                        modifier = modifier
                            .width(150.dp)
                            .height(200.dp)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(BASE_IMAGE + movie.favorite?.posterPath)
                                .crossfade(500)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Column {
                        Text(
                            text = movie.favorite?.title!!,
                            color = coolWhite,
                            modifier = Modifier.padding(start = 8.dp, top = 5.dp),
                            fontSize = 14.sp
                        )
                        Row(
                            modifier = Modifier.padding(top = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_round_star_24),
                                contentDescription = null,
                                tint = coolWhite,
                                modifier = modifier.padding(start = 5.dp)
                            )
                            Text(
                                text = movie.favorite?.voteAverage!!.toInt().toString(),
                                color = philippineSilver,
                                modifier = Modifier.padding(start = 8.dp),
                                fontSize = 14.sp
                            )

                        }
                        Row(
                            modifier = Modifier.padding(top = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_round_language_24),
                                contentDescription = null,
                                tint = coolWhite,
                                modifier = modifier.padding(start = 5.dp)
                            )
                            Text(
                                text = movie.favorite?.originalLanguage!!,
                                color = philippineSilver,
                                modifier = Modifier.padding(start = 8.dp),
                                fontSize = 14.sp
                            )

                        }
                        Row(
                            modifier = Modifier.padding(top = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_round_calendar_today_24),
                                contentDescription = null,
                                tint = coolWhite,
                                modifier = modifier.padding(start = 5.dp)
                            )
                            Text(
                                text = movie.favorite?.releaseDate!!.split("-")[0],
                                color = philippineSilver,
                                modifier = Modifier.padding(start = 8.dp),
                                fontSize = 14.sp
                            )

                        }
                        Row(

                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Text(
                                text = "Get more Info ",
                                color = scarlet,
                                modifier = Modifier.padding(start = 8.dp, top = 26.dp),
                                fontSize = 14.sp
                            )
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = null,
                                tint = scarlet, modifier = Modifier.offset(y = 12.dp)

                            )

                        }


                    }

                }
            }
        }
    }

}

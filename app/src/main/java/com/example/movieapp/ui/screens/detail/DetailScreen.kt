package com.example.movieapp.ui.screens.detail

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.data.db.FavoriteEntity
import com.example.movieapp.data.models.detail.ResponseDetail
import com.example.movieapp.data.models.detail.ResponseMovieActors
import com.example.movieapp.ui.screens.favorite.FavoriteViewModel
import com.example.movieapp.ui.theme.chineseBlack
import com.example.movieapp.ui.theme.chineseBlackAlpha
import com.example.movieapp.ui.theme.coolWhite
import com.example.movieapp.ui.theme.crayola
import com.example.movieapp.ui.theme.philippineSilver
import com.example.movieapp.ui.theme.raisinBlack
import com.example.movieapp.ui.theme.scarlet
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.androidColors
import com.example.movieapp.utils.network.NetworkStatus

@Composable
fun DetailScreen(movieId: Int, navController: NavController) {
    val viewModel = hiltViewModel<DetailViewModel>()
    val favViewModel = hiltViewModel<FavoriteViewModel>()
    LaunchedEffect(Unit) {

        viewModel.loadHomeData(movieId)
        favViewModel.isMovieInFavorite(movieId)


    }

    val uiState = viewModel.uiState
    val context = LocalContext.current
    when {
        uiState.isLoading -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = crayola)
            }
        }

        else -> {

            val detailOverView = uiState.detailOverView
            val actors = uiState.actors
            val state = rememberScrollState()



            if (detailOverView is NetworkStatus.Data && actors is NetworkStatus.Data) {
                Column(
                    modifier = Modifier.verticalScroll(state)
                ) {
                    DetailTopSection(
                        onBackClicked = { navController.popBackStack() },
                        onLikeClicked = {
                            favViewModel.isFavorite = !favViewModel.isFavorite
                            val entity = FavoriteEntity(movieId, detailOverView.data)
                            if (!favViewModel.isFavorite) {

                                favViewModel.deleteFavorite(entity)
                            } else {

                                favViewModel.saveFavorite(entity)
                            }

                        },
                        movie = detailOverView.data!!,
                        modifier = Modifier.height(500.dp),
                        isFav = favViewModel.isFavorite,

                    )

                    Spacer(Modifier.height(8.dp))
                    SummerySection(detailOverView.data)
                    Spacer(Modifier.height(8.dp))
                    ActorsSection(actors.data!!)

                }

            }


            if (detailOverView is NetworkStatus.Error) {
                Toast.makeText(context, detailOverView.error, Toast.LENGTH_SHORT).show()
            }
            if (actors is NetworkStatus.Error) {
                Toast.makeText(context, actors.error, Toast.LENGTH_SHORT).show()
            }

        }
    }


}

@Composable
fun ActorsSection(movie: ResponseMovieActors) {
    var actorsName = ""
    Column(

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(painterResource(R.drawable.ic_round_person_24), contentDescription = null)
            Text(
                text = "Actors",
                color = coolWhite,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 6.dp)
            )
        }
        Spacer(Modifier.height(8.dp))


        movie.cast!!.forEach {
            actorsName += it.name!! + " , "
        }
        Text(
            text = actorsName,
            color = philippineSilver,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 6.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Spacer(Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(movie.cast!!) { index ->

                ActorsCard(index.profilePath!!)
            }
        }

    }
}

@Composable
fun ActorsCard(movieActor: String) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .size(90.dp), shape = RoundedCornerShape(10.dp)
    ) {
        AsyncImage(

            model = ImageRequest.Builder(context)
                .data(Constants.BASE_IMAGE + movieActor)
                .crossfade(500)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()

        )

    }
}

@Composable
fun SummerySection(movie: ResponseDetail) {
    Column(

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(painterResource(R.drawable.ic_round_info_24), contentDescription = null)
            Text(
                text = "Summery",
                color = coolWhite,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 6.dp)
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            fontSize = 12.sp,
            color = philippineSilver,
            text = movie.overview!!,
            textAlign = TextAlign.Justify, modifier = Modifier.padding(horizontal = 12.dp)
        )

    }
}

@Composable
fun DetailTopSection(
    onBackClicked: () -> Unit,
    onLikeClicked: () -> Unit,
    movie: ResponseDetail,
    modifier: Modifier = Modifier,
    isFav: Boolean
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxWidth()


    ) {


        AsyncImage(

            model = ImageRequest.Builder(context)
                .data(Constants.BASE_IMAGE + movie.posterPath)
                .crossfade(500)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(.1f)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            AsyncImage(

                model = ImageRequest.Builder(context)
                    .data(Constants.BASE_IMAGE + movie.posterPath)
                    .crossfade(500)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .width(250.dp)
                    .height(320.dp)

            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 18.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                androidColors.Transparent,
                                chineseBlackAlpha,
                                chineseBlack,
                            )
                        )
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                Text(
                    movie.title!!,
                    fontSize = 20.sp,
                    color = coolWhite,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.padding(top = 10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(30.dp)

                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,


                        ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_round_star_24),
                            contentDescription = null,
                            tint = coolWhite
                        )
                        Text(
                            movie.voteAverage!!.toInt().toString(),
                            fontSize = 14.sp,
                            color = philippineSilver,
                            fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,


                        ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_round_access_time_24),
                            contentDescription = null,
                            tint = coolWhite
                        )
                        Text(
                            "${movie.runtime} min",
                            fontSize = 14.sp,
                            color = philippineSilver,
                            fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Icon(
                            modifier = Modifier.scale(.9f),
                            painter = painterResource(R.drawable.ic_round_calendar_today_24),
                            contentDescription = null,
                            tint = coolWhite
                        )
                        Text(
                            movie.releaseDate!!,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            fontSize = 14.sp,
                            color = philippineSilver,
                            fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 4.dp)
                        )
                    }

                }

            }


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(shape = CircleShape, color = raisinBlack) {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = philippineSilver
                    )
                }
            }
            Surface(shape = CircleShape, color = raisinBlack) {
                IconButton(onClick = onLikeClicked) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = if (isFav) scarlet else philippineSilver
                    )
                }
            }

        }


    }


}

@Composable
fun DetailTopSectionPrev(onBackClicked: () -> Unit, onLikeClicked: () -> Unit,favViewModel: FavoriteViewModel) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.6f)

    ) {


        AsyncImage(

            model = ImageRequest.Builder(context)
                .data(R.drawable.pic)
                .crossfade(500)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(.1f)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            AsyncImage(

                model = ImageRequest.Builder(context)
                    .data(R.drawable.pic)
                    .crossfade(500)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .width(250.dp)
                    .height(320.dp)

            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 18.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                androidColors.Transparent,
                                chineseBlackAlpha,
                                chineseBlack,
                            )
                        )
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                Text(
                    "  movie",
                    fontSize = 20.sp,
                    color = coolWhite,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.padding(top = 10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(30.dp)

                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,


                        ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_round_star_24),
                            contentDescription = null,
                            tint = coolWhite
                        )
                        Text(
                            "1",
                            fontSize = 14.sp,
                            color = philippineSilver,
                            fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,


                        ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_round_access_time_24),
                            contentDescription = null,
                            tint = coolWhite
                        )
                        Text(
                            "10 min",
                            fontSize = 14.sp,
                            color = philippineSilver,
                            fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Icon(
                            modifier = Modifier.scale(.9f),
                            painter = painterResource(R.drawable.ic_round_calendar_today_24),
                            contentDescription = null,
                            tint = coolWhite
                        )
                        Text(
                            "1 apr 2025",
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            fontSize = 14.sp,
                            color = philippineSilver,
                            fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 4.dp)
                        )
                    }

                }

            }


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(shape = CircleShape, color = raisinBlack) {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = philippineSilver
                    )
                }
            }
            Surface(shape = CircleShape, color = raisinBlack) {
                IconButton(onClick = onLikeClicked) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = philippineSilver
                    )
                }
            }

        }


    }


}


@Preview
@Composable
private fun DetailPrev() {

}
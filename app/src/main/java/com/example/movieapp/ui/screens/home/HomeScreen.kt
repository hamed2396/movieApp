package com.example.movieapp.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.data.models.home.ResponseGenres
import com.example.movieapp.data.models.home.ResponseTopRated
import com.example.movieapp.ui.theme.CoolYellow
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.chineseBlack
import com.example.movieapp.ui.theme.chineseBlackAlpha
import com.example.movieapp.ui.theme.coolWhite
import com.example.movieapp.ui.theme.crayola
import com.example.movieapp.ui.theme.philippineSilver
import com.example.movieapp.ui.theme.raisinBlack
import com.example.movieapp.utils.Constants.BASE_IMAGE
import com.example.movieapp.utils.androidColors
import com.example.movieapp.utils.network.NetworkStatus
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val context = LocalContext.current

    val data = viewModel.topRatedMovies
    val genresList = viewModel.genresList

    Column(modifier = Modifier.fillMaxSize()) {

        // ----- Top Rated section (یا loader / error placeholder) -----
        when (data) {
            is NetworkStatus.Data -> {
                TopRatedMovieSection(data = data.data!!.results!!)
            }
            is NetworkStatus.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = crayola)
                }
            }
            is NetworkStatus.Error -> {
                // نمایش متن خطا یا Toast با LaunchedEffect
                LaunchedEffect(data) {
                    Toast.makeText(context, data.error, Toast.LENGTH_SHORT).show()
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error loading top rated", color = Color.Red)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))


        when (genresList) {
            is NetworkStatus.Data -> {
                GenresSection(genresList = genresList.data!!.genres!!)
            }
            is NetworkStatus.Loading -> {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = crayola)
                }
            }
            is NetworkStatus.Error -> {
                LaunchedEffect(genresList) {
                    Toast.makeText(context, genresList.error, Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
}



@Composable
fun TopRatedMovieSection(data: List<ResponseTopRated.Result>) {
    val context = LocalContext.current
    val pagerState = rememberPagerState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
    ) {
        HorizontalPager(
            count = data.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->

            val movie = data[page]
            val posterPath = movie.posterPath ?: movie.backdropPath ?: ""
            val imageUrl = BASE_IMAGE + posterPath

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(imageUrl)
                        .crossfade(500)
                        .build(),
                    contentDescription = movie.title ?: "Movie Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    androidColors.Transparent,
                                    chineseBlackAlpha,
                                    chineseBlack,
                                )
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 48.dp, start = 16.dp, end = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = movie.title ?: "",
                            color = coolWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_round_star_24),
                                contentDescription = null,
                                tint = CoolYellow,
                                modifier = Modifier
                                    .offset(y = (-2).dp)
                                    .size(18.dp)
                            )
                            Text(
                                modifier = Modifier.padding(start = 5.dp),
                                text = (movie.voteAverage?.toInt()?.toString() + " |"),
                                color = philippineSilver,
                                fontSize = 18.sp,
                            )
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                text = (movie.originalLanguage + " |"),
                                color = philippineSilver,
                                fontSize = 18.sp,
                            )
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                text = movie.releaseDate?.split('-')?.get(0) ?: "-",
                                color = philippineSilver,
                                fontSize = 18.sp,
                            )
                        }
                    }
                }
            }
        }

        // Dot indicator پایین صفحه
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            activeColor = CoolYellow,
            inactiveColor = philippineSilver,
            indicatorWidth = 8.dp,
            spacing = 8.dp
        )
    }
}

@Composable
fun GenresSection(genresList: List<ResponseGenres.Genre>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(text = "Genres", color = CoolYellow,modifier = Modifier.padding(start = 8.dp))
        Spacer(modifier = Modifier.height(5.dp))
        GenresText(genresList)
    }
}

@Composable
fun GenresText(genresList: List<ResponseGenres.Genre>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .horizontalScroll(
                rememberScrollState()
            )
    )
    {
        genresList.forEach {genres->
            Surface(
                color = raisinBlack,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.clickable{}

            ) {
                Text(genres.name!!, color = coolWhite, modifier = Modifier.padding(5.dp), fontSize = 12.sp)


            }
            Spacer(modifier = Modifier.padding(end = 8.dp))

        }


    }

}


@Preview
@Composable
private fun HomeScreenPrev() {
    MovieAppTheme {
        GenresText(listOf())
    }
}
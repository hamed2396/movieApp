package com.example.movieapp.ui.screens.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import com.example.movieapp.data.models.home.ResponseTopRated
import com.example.movieapp.ui.theme.CoolYellow
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.chineseBlack
import com.example.movieapp.ui.theme.chineseBlackAlpha
import com.example.movieapp.ui.theme.coolWhite
import com.example.movieapp.ui.theme.crayola
import com.example.movieapp.ui.theme.philippineSilver
import com.example.movieapp.utils.Constants.BASE_IMAGE
import com.example.movieapp.utils.androidColors
import com.example.movieapp.utils.network.NetworkStatus
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val context=LocalContext.current
    val data=viewModel.topRatedMovies
    when (data) {
        is NetworkStatus.Data -> {
            Column(modifier = Modifier.fillMaxSize()) {
                TopRatedMovieSection(data.data!!.results!!)

            }
        }

        is NetworkStatus.Error -> {
            Toast.makeText(context, data.error, Toast.LENGTH_SHORT).show()
        }

        is NetworkStatus.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                CircularProgressIndicator(color = crayola)

            }
        }
    }

}

/*
@Composable
fun TopRatedMovieSection(
    movies: List<ResponseTopRated.Result>
) {
    val context = LocalContext.current

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp), // ارتفاع دلخواه برای کل LazyRow
        contentPadding = PaddingValues(horizontal = 16.dp), // فاصله از طرفین
        horizontalArrangement = Arrangement.spacedBy(16.dp) // فاصله بین آیتم‌ها
    ) {
        items(movies) { movie ->
            Column(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .height(400.dp) // ارتفاع آیتم، متناسب با LazyRow
            ) {
                val posterPath = movie.posterPath ?: movie.backdropPath ?: ""
                val imageUrl = BASE_IMAGE + posterPath

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(imageUrl)
                        .crossfade(500)
                        .build(),
                    contentDescription = movie.title ?: "Movie Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = movie.title ?: "",
                    color = coolWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_round_star_24),
                        contentDescription = null,
                        tint = CoolYellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = movie.voteAverage?.toString() ?: "-",
                        color = philippineSilver,
                        fontSize = 14.sp
                    )
                }

                Text(
                    text = movie.originalLanguage ?: "-",
                    color = philippineSilver,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Text(
                    text = movie.releaseDate?.split('-')?.get(0) ?: "-",
                    color = philippineSilver,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
*/
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

                // گرادیانت پایین تصویر
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
                                modifier = Modifier.offset(y = (-2).dp).size(18.dp)
                            )
                            Text(
                                modifier = Modifier.padding(start = 5.dp),
                                text = (movie.voteAverage?.toInt()?.toString() + " |"),
                                color = philippineSilver,
                                fontSize = 18.sp,
                            )
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                text = (movie.originalLanguage +" |"),
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




@Preview
@Composable
private fun HomeScreenPrev() {
    MovieAppTheme {
        HomeScreen()
    }
}
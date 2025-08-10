package com.example.movieapp.ui.screens.home

import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.ui.theme.CoolYellow
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.chineseBlack
import com.example.movieapp.ui.theme.chineseBlackAlpha
import com.example.movieapp.ui.theme.coolWhite
import com.example.movieapp.ui.theme.philippineSilver
import com.example.movieapp.utils.androidColors
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun HomeScreen() {

    Column(modifier = Modifier.fillMaxSize()) {
        TopRatedMovieSection()

    }
}


@Composable
fun TopRatedMovieSection() {
    val context = LocalContext.current
    val images = listOf(
        R.drawable.pic,
        R.drawable.pic,
        R.drawable.pic
    )

    val pagerState = rememberPagerState()

    Box(
        modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth()
    ) {
        HorizontalPager(
            count = images.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(context)
                    .data(images[page])
                    .crossfade(599)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

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
                    .padding(bottom = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Dark Knight",
                    color = coolWhite,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_round_star_24),
                        contentDescription = null,
                        tint = CoolYellow,
                        modifier = Modifier.offset(y = (-2).dp)
                    )
                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = "10 |",
                        color = philippineSilver,
                        fontSize = 18.sp,
                    )
                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = "en |",
                        color = philippineSilver,
                        fontSize = 18.sp,
                    )
                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = "1995",
                        color = philippineSilver,
                        fontSize = 18.sp,
                    )
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
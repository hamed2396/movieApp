package com.example.movieapp.ui.screens.intro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.movieapp.R
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.utils.MyScreens

@Composable
fun IntroScreen(modifier: Modifier = Modifier, navigation: NavController) {

    ShowAnimation(navController = navigation)
}

@Composable
fun ShowAnimation(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.movie))

        val progress by animateLottieCompositionAsState(
            composition = composition,

            speed = 1f
        )


        LottieAnimation(
            composition = composition,
            progress = { progress },

            )
        if (progress == 1f) {
            navController.navigate(MyScreens.RegisterScreen.route) {
                navController.popBackStack()
            }
        }

    }

}


@Preview
@Composable
private fun IntroScreenPrev() {
    MovieAppTheme {
        val navController = rememberNavController()
        IntroScreen(navigation = navController)
    }
}
package com.example.movieapp.ui.screens.search

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.data.models.ResponseSearch
import com.example.movieapp.ui.theme.coolWhite
import com.example.movieapp.ui.theme.philippineSilver
import com.example.movieapp.ui.theme.scarlet
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.Constants.BASE_IMAGE
import com.example.movieapp.utils.network.NetworkStatus
import com.example.movieapp.utils.screens.MyScreens
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(navController: NavController) {
    val viewModel = hiltViewModel<SearchViewModel>()
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = viewModel.title,
            onValueChange = { viewModel.onTitleChanged(it)  },
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 16.dp)
                .fillMaxWidth(),
            singleLine = true,
            label = { Text("Search") }
        )

        Spacer(Modifier.padding(top = 10.dp))
        when (viewModel.movieData) {
            is NetworkStatus.Data -> {

                SearchMovies(searchList = viewModel.movieData.data!!.results!!) {
                    navController.navigate(MyScreens.DetailScreen.route+"/$it")
                }
            }
            is NetworkStatus.Error -> {

                Toast.makeText(context, "Error retrieving data", Toast.LENGTH_SHORT).show()
            }

            is NetworkStatus.Loading -> {

                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

                    CircularProgressIndicator()
                }
            }

            is NetworkStatus.Idle<*> ->{}
        }

    }
}

@Composable
fun SearchMovies(
    modifier: Modifier = Modifier,
    searchList: List<ResponseSearch.Result>,
    onItemClicked: (Int) -> Unit
) {
    val context = LocalContext.current

    LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {

        items(searchList) { movie ->
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)
                    .clickable { onItemClicked(movie.id!!) }
            ) {

                Row(modifier = Modifier.padding(start = 8.dp, top = 8.dp)) {
                    Card(
                        modifier = modifier
                            .width(150.dp)
                            .height(200.dp)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(BASE_IMAGE + movie.posterPath)
                                .crossfade(500)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Column {
                        Text(
                            text = movie.title!!,
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
                                text = movie.voteAverage!!.toInt().toString(),
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
                                text = movie.originalLanguage!!,
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
                                text = movie.releaseDate!!.split("-")[0],
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


@Preview
@Composable
private fun SearchPrev() {

}
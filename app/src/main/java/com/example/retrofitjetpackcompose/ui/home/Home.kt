package com.example.retrofitjetpackcompose.ui.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.example.retrofitjetpackcompose.BuildConfig
import com.example.retrofitjetpackcompose.data.remote.model.MovieItem
import com.example.retrofitjetpackcompose.ui.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val movies = viewModel.popularMoviesItems.collectAsLazyPagingItems()
    Column {
        Text(
            text = "Popular Movies",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(start = 10.dp)
        )
        LazyVerticalGrid(columns = GridCells.Adaptive(120.dp), content = {
            items(movies.itemCount) { i ->
                Row {
                    movies[i]?.let {
                        MovieCard(
                            movie = it,
                            modifier = Modifier,
                            navController = navController
                        )
                    }
                }
            }
        }
        )
    }
}


@Composable
fun MovieCard(movie: MovieItem, modifier: Modifier, navController: NavController) {
    val image = rememberImagePainter(data = BuildConfig.ORIGINAL_IMAGE_URL + movie.posterPath)
    val id = movie.movieId
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
            .fillMaxSize()
            .clickable {
                navController.navigate(Screen.MovieDetailScreen.route + "?movieId=${movie?.movieId.toString()}&moviesTitle=${movie?.title}")
            }
    ) {
        Column {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )
        }
    }
}

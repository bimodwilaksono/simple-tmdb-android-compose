package com.example.retrofitjetpackcompose.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.retrofitjetpackcompose.BuildConfig
import com.example.retrofitjetpackcompose.data.remote.model.MovieDetailResponse
import com.example.retrofitjetpackcompose.data.remote.model.MovieVideoResponse
import com.example.retrofitjetpackcompose.ui.Screen
import com.gowtham.ratingbar.RatingBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Details(
    navController: NavController,
    title: String,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val image =
        rememberImagePainter(data = BuildConfig.ORIGINAL_IMAGE_URL + viewModel.movieDetailResponse.value.backdrop_path)

    Scaffold(topBar = {
        ToolBar(title = title, onBack = { navController.popBackStack() })
    }) { paddingValues ->
        Box(modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())) {
            val details = viewModel.movieDetailResponse.value
            val videos = viewModel.getVideosResponse.value
            if (details.id != null) {
                LazyColumn(content = {
                    item {
                        Image(
                            painter = image,
                            contentDescription = "poster movie",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(top = 60.dp)
                        )
                    }
                    item { ItemTitle(navController, details, videos) }
                    item { ItemOverview(details) }
                })
            }
        }


    }
}

@ExperimentalMaterial3Api
@Composable
fun ToolBar(title: String, onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        },
    )
}

@Composable
fun ItemOverview(response: MovieDetailResponse) {

    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Overview",
        style = MaterialTheme.typography.titleSmall,
        maxLines = 1,
        modifier = Modifier.padding(start = 15.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    val lineHeight = MaterialTheme.typography.titleSmall.fontSize * 4 / 3
    Text(
        text = response.overview ?: "",
        style = MaterialTheme.typography.bodyLarge,
        lineHeight = lineHeight,
        modifier = Modifier.padding(horizontal = 15.dp)
    )
}

@Composable
fun ItemTitle(
    navController: NavController,
    response: MovieDetailResponse,
    videos: MovieVideoResponse
) {
    response.title?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.titleSmall,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

    }
    Text(
        text = response.release_date?.split("-")?.get(0) ?: "",
        style = MaterialTheme.typography.titleSmall,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(10.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RatingBar(
            value = (response.vote_average?.toFloat() ?: 0f) / 2,
            onRatingChanged = {},
            onValueChange = {},
            modifier = Modifier.padding(end = 15.dp)
        )
        Divider(
            modifier = Modifier
                .height(20.dp)
                .width(3.dp)
                .background(Color.Gray)
                .padding(end = 15.dp)
        )
        Row {
            Icon(Icons.Filled.PlayArrow, contentDescription = "play trailer")
            Text(
                text = "Play Trailer",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .clickable {
                        val item = videos.results?.last { it?.type == "Trailer" }
                        navController.navigate(Screen.MovieVideoScreen.route + "youtubeId=${item?.key}")
                    }
            )
        }
    }

}



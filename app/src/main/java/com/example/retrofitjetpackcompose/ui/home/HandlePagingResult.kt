package com.example.retrofitjetpackcompose.ui.home

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.retrofitjetpackcompose.data.remote.model.MovieItem

@Composable
fun HandlePagingResult(movieItem: LazyPagingItems<MovieItem>) {
    movieItem.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {

            else -> {}
        }
    }
}
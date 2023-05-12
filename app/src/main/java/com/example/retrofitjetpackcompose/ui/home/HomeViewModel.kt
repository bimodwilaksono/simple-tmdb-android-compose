package com.example.retrofitjetpackcompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.retrofitjetpackcompose.data.remote.model.MovieItem
import com.example.retrofitjetpackcompose.domain.UseCases
import com.example.retrofitjetpackcompose.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(useCases: UseCases)
    : ViewModel() {
    var popularMoviesItems: Flow<PagingData<MovieItem>> = emptyFlow()

    init {
        viewModelScope.launch {
            popularMoviesItems = useCases.popularMovie.invoke(Constants.LANG)
        }
    }
}
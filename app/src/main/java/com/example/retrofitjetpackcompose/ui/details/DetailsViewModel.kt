package com.example.retrofitjetpackcompose.ui.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitjetpackcompose.data.remote.model.MovieDetailResponse
import com.example.retrofitjetpackcompose.data.remote.model.MovieVideoResponse
import com.example.retrofitjetpackcompose.domain.UseCases
import com.example.retrofitjetpackcompose.util.Constants
import com.example.retrofitjetpackcompose.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    useCases: UseCases, savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _movieDetailsResponse: MutableState<MovieDetailResponse> =
        mutableStateOf(MovieDetailResponse())
    val movieDetailResponse: State<MovieDetailResponse> = _movieDetailsResponse

    private val _getVideosResponse: MutableState<MovieVideoResponse> = mutableStateOf(
        MovieVideoResponse()
    )
    val getVideosResponse: State<MovieVideoResponse> = _getVideosResponse

    init {
        savedStateHandle.get<String>("movieId")?.let { movieId ->
            if (movieId.isNotEmpty()){
                viewModelScope.launch {
                    useCases.movieDetail.invoke(Constants.LANG, movieId).collect {
                        when (it) {

                            is NetworkResult.Success -> {
                                it.value.body()?.let { response ->
                                    _movieDetailsResponse.value = response
                                    delay(1000)
                                }
                            }

                            is NetworkResult.Failure -> TODO()
                           is NetworkResult.Loading -> TODO()
                        }

                    }
                    useCases.movieVideo.invoke(Constants.LANG, movieId).collect {
                        when (it) {

                            is NetworkResult.Success -> {
                                it.value.body()?.let { response ->
                                    _getVideosResponse.value = response
                                    delay(1000)
                                }
                            }

                            is NetworkResult.Failure -> TODO()
                           is NetworkResult.Loading -> TODO()
                        }
                    }
                }
            }

        }
    }
}
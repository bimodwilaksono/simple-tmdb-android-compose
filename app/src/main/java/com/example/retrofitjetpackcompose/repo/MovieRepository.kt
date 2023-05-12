package com.example.retrofitjetpackcompose.repo

import androidx.paging.PagingData
import com.example.retrofitjetpackcompose.data.remote.model.MovieDetailResponse
import com.example.retrofitjetpackcompose.data.remote.model.MovieItem
import com.example.retrofitjetpackcompose.data.remote.model.MovieVideoResponse
import com.example.retrofitjetpackcompose.data.remote.model.PopularMovieResponse
import com.example.retrofitjetpackcompose.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

interface MovieRepository  {

    suspend fun popularMovie(lang:String): Flow<PagingData<MovieItem>>
    suspend fun movieDetails(lang: String, movieId: String): Flow<NetworkResult<Response<MovieDetailResponse>>>
    suspend fun movieVideos(lang: String, movieId: String): Flow<NetworkResult<Response<MovieVideoResponse>>>

}
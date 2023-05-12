package com.example.retrofitjetpackcompose.domain.details

import com.example.retrofitjetpackcompose.data.remote.MovieService
import com.example.retrofitjetpackcompose.data.remote.model.MovieVideoResponse
import com.example.retrofitjetpackcompose.repo.MovieRepository
import com.example.retrofitjetpackcompose.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MovieVideo @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<MovieVideoResponse>>> {
        return movieRepository.movieVideos(lang, movieId)
    }
}
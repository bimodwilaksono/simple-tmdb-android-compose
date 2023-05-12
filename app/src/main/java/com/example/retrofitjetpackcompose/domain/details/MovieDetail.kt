package com.example.retrofitjetpackcompose.domain.details

import com.example.retrofitjetpackcompose.data.remote.model.MovieDetailResponse
import com.example.retrofitjetpackcompose.repo.MovieRepository
import com.example.retrofitjetpackcompose.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MovieDetail @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<MovieDetailResponse>>> {
        return movieRepository.movieDetails(lang, movieId)
    }
}
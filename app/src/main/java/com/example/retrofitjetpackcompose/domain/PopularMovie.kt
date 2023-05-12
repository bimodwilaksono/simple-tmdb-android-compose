package com.example.retrofitjetpackcompose.domain

import androidx.paging.PagingData
import com.example.retrofitjetpackcompose.data.remote.MovieService
import com.example.retrofitjetpackcompose.data.remote.model.MovieItem
import com.example.retrofitjetpackcompose.data.remote.model.PopularMovieResponse
import com.example.retrofitjetpackcompose.repo.MovieRepository
import com.example.retrofitjetpackcompose.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class PopularMovie @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(
        lang: String
    ): Flow<PagingData<MovieItem>> {
        return movieRepository.popularMovie(lang)
    }
}
package com.example.retrofitjetpackcompose.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.retrofitjetpackcompose.BuildConfig
import com.example.retrofitjetpackcompose.data.remote.model.MovieDetailResponse
import com.example.retrofitjetpackcompose.data.remote.model.MovieItem
import com.example.retrofitjetpackcompose.data.remote.model.MovieVideoResponse
import com.example.retrofitjetpackcompose.data.remote.model.PopularMovieResponse
import com.example.retrofitjetpackcompose.data.remote.paging.PopularPagingSource
import com.example.retrofitjetpackcompose.repo.MovieRepository
import com.example.retrofitjetpackcompose.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class MovieService @Inject constructor(private val movieApi: MovieApi) : MovieRepository {
    override suspend fun popularMovie(lang: String): Flow<PagingData<MovieItem>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { PopularPagingSource(movieApi, lang) }
    ).flow


    override suspend fun movieDetails(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<MovieDetailResponse>>> {
        return flow {
            try {
                val response = movieApi.getMovieDetail(movieId = movieId, language = lang)
                emit(NetworkResult.Success(response))
            } catch (throwable: Throwable) {

            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun movieVideos(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<MovieVideoResponse>>> {
        return flow {
            try {
                val response = movieApi.getMovieVideos(movieId = movieId, language = lang)
                emit(NetworkResult.Success(response))
            } catch (throwable: Throwable) {

            }
        }.flowOn(Dispatchers.IO)
    }
}
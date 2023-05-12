package com.example.retrofitjetpackcompose.data.remote

import com.example.retrofitjetpackcompose.data.remote.model.MovieDetailResponse
import com.example.retrofitjetpackcompose.data.remote.model.MovieVideoResponse
import com.example.retrofitjetpackcompose.data.remote.model.PopularMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int?,
        @Query("language") language: String?,
    ): PopularMovieResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: String,
        @Query("language") language: String?
    ): Response<MovieDetailResponse>

    @GET("movie/{movieId}/videos")
    suspend fun getMovieVideos(
        @Path("movieId") movieId: String,
        @Query("language") language: String?
    ): Response<MovieVideoResponse>
}
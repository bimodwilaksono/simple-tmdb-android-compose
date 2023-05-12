package com.example.retrofitjetpackcompose.domain

import com.example.retrofitjetpackcompose.domain.details.MovieDetail
import com.example.retrofitjetpackcompose.domain.details.MovieVideo

data class UseCases(
    val popularMovie: PopularMovie,
    val movieDetail: MovieDetail,
    val movieVideo: MovieVideo
)
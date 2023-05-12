package com.example.retrofitjetpackcompose.data.remote.model

import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val movieItems: List<MovieItem>,

    @field:SerializedName("total_pages")
    val total_pages: Int,

    @field:SerializedName("total_results")
    val total_results: Int
)
package com.example.retrofitjetpackcompose.data.remote.model

data class MovieVideoResponse(
    val id: Int? = null,
    val results: List<ResultVideo?>? = null
)

data class ResultVideo(
    val id: String? = null,
    val iso_3166_1: String? = null,
    val iso_639_1: String? = null,
    val key: String? = null,
    val name: String? = null,
    val official: Boolean? = null,
    val published_at: String? = null,
    val site: String? = null,
    val size: Int? = null,
    val type: String? = null
)
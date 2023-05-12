package com.example.retrofitjetpackcompose.ui

sealed class Screen(val route: String){
    object Home: Screen("home_screen")
    object MovieDetailScreen: Screen("movie_details")
    object MovieVideoScreen: Screen("video_player_screen")
}

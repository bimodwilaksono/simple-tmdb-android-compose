package com.example.retrofitjetpackcompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.retrofitjetpackcompose.ui.details.Details
import com.example.retrofitjetpackcompose.ui.details.MovieVideoScreen
import com.example.retrofitjetpackcompose.ui.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        //Home Screen
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController) // TODO
        }

        //Movie Detail Screen
        composable(
            route = Screen.MovieDetailScreen.route + "?movieId={movieId}&moviesTitle={moviesTitle}",
            arguments = listOf(
            navArgument(name = "movieId") {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument(name = "moviesTitle") {
                type = NavType.StringType
                defaultValue = ""
            }
        )) {
            val moviesTitle = it.arguments?.getString("moviesTitle") ?: ""
            val movieId = it.arguments?.getString("movieId") ?: ""
            Details(navController = navController, title = moviesTitle)
        }

        //Video Screen
        composable(route = Screen.MovieVideoScreen.route + "youtubeId={youtubeId}",
            arguments = listOf(
                navArgument(name = "youtubeId") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val youtubeId = it.arguments?.getString("youtubeId") ?: ""
            MovieVideoScreen(navController = navController, youtubeId = youtubeId)
        }

    }
}
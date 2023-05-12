package com.example.retrofitjetpackcompose.di

import com.example.retrofitjetpackcompose.data.remote.MovieService
import com.example.retrofitjetpackcompose.repo.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieRepositoryBind {

    @Binds
    @Singleton
    abstract fun bindRepository(movieService: MovieService): MovieRepository

}
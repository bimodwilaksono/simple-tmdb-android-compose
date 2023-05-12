package com.example.retrofitjetpackcompose.di

import com.example.retrofitjetpackcompose.BuildConfig
import com.example.retrofitjetpackcompose.data.remote.MovieApi
import com.example.retrofitjetpackcompose.domain.PopularMovie
import com.example.retrofitjetpackcompose.domain.UseCases
import com.example.retrofitjetpackcompose.domain.details.MovieDetail
import com.example.retrofitjetpackcompose.domain.details.MovieVideo
import com.example.retrofitjetpackcompose.repo.MovieRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun clientInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
            val newUrl = request.url.newBuilder().build()

            val newRequest = request.newBuilder()
                .url(newUrl)
                .addHeader("Authorization", "Bearer " + BuildConfig.API_KEY)
                .build()
            chain.proceed(newRequest)
        }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder().also { client ->
                    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                    }
                    client.addInterceptor(httpLoggingInterceptor)
                    client.addNetworkInterceptor(clientInterceptor())
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }


    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    @Singleton
    fun useCases(movieRepository: MovieRepository): UseCases = UseCases(
        PopularMovie(movieRepository),
        MovieDetail(movieRepository),
        MovieVideo(movieRepository)
    )
}
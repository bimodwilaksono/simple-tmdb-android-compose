package com.example.retrofitjetpackcompose.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.retrofitjetpackcompose.data.remote.MovieApi
import com.example.retrofitjetpackcompose.data.remote.model.MovieItem
import com.example.retrofitjetpackcompose.util.Constants.NETWORK_PAGE_SIZE
import com.example.retrofitjetpackcompose.util.Constants.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class PopularPagingSource(private val movieApi: MovieApi, private val lang: String) :
    PagingSource<Int, MovieItem>() {
    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val data = movieApi.getPopularMovie(page = position, language = lang)
            val nextKey = if (data.movieItems?.isEmpty() == true) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            val prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1
            LoadResult.Page(
                data = data.movieItems!!,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}
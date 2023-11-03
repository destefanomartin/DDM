package com.utn.segundoparcial

import com.utn.segundoparcial.domain.models.Movie
import com.utn.segundoparcial.domain.models.Page
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("movie/popular?api_key=3de7bc9bbe3f8f2c96f83b1604a994ca&language=en-us&page=1")
    suspend fun getPage(@Query("page") page: Int = 1): Response<PageResponse>

    @GET("posts/{id}")
    suspend fun getMovieById(@Path("id") id: Int): Response<MoviesResponse>

    @GET("comments")
    suspend fun getMoviesForPage(@Query("pageResult") result : MutableList<MoviesResponse>): List<Movie>

}
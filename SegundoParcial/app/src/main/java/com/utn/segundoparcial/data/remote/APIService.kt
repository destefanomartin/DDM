package com.utn.segundoparcial.data.remote

import com.utn.segundoparcial.data.remote.responses.PageResponse
import com.utn.segundoparcial.data.models.MovieDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "3de7bc9bbe3f8f2c96f83b1604a994ca"
    }

    @GET("movie/popular?api_key=3de7bc9bbe3f8f2c96f83b1604a994ca&language=es-us&page=1")
    suspend fun getPage(@Query("page") page: Int = 1): Response<PageResponse>




    @GET("movie/{movieId}?")
    suspend fun getMovieById(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "es-ES",
    ): Response<MovieDetailsResponse>

}
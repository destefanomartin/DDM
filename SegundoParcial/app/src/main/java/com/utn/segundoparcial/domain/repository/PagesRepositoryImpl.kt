package com.utn.segundoparcial.domain.repository

import android.util.Log
import com.utn.segundoparcial.APIService
import com.utn.segundoparcial.MoviesResponse
import com.utn.segundoparcial.PageResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.utn.segundoparcial.domain.models.Movie
import com.utn.segundoparcial.domain.models.Page
import com.utn.segundoparcial.toDomain
import com.utn.segundoparcial.toMovie
import com.utn.segundoparcial.toPage


class PagesRepositoryImpl : PagesRepository {


    companion object {
        const val TAG = "PagesRepositoryImpl"
    }

    private val apiClient: APIService by lazy {
        // OkHttp client with logging interceptor and api key query param into all requests
        val httpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
            )
            .build()

        // Retrofit client with Gson converter (JSON -> data class) and OkHttp client
        val retrofit = Retrofit.Builder()
            .baseUrl(APIService.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@lazy retrofit.create(APIService::class.java)
    }

    override suspend fun getPage(page: Int): MutableList<Movie>? {
        return try {
            val response = apiClient.getPage(page)
            if (response.isSuccessful) {
                response?.body()?.results?.map { it.toDomain() }?.toMutableList()
            } else {
                null
            }
        } catch (e: Exception) {
            Log.d(TAG, "Exception caught: $e")
            null
        }
    }

    override suspend fun getMoviesById(id: Int): Movie? {
        return try {
            val response = apiClient.getMovieById(id)
            if (response.isSuccessful) {
                response.body()?.toMovie()
            } else {
                null
            }
        } catch (e: Exception) {
            Log.d(TAG, "Exception caught: $e")
            null
        }
    }

    override suspend fun getMoviesForPage(page : Int): List<Movie> {
        /*try {
            val response = apiClient.getMoviesForPage(result)
            if (response.isSuccessful) {
                response.body()?.map { it.toMovie() }
            } else {
                null
            }
        } catch (e: Exception) {
            Log.d(TAG, "Exception caught: $e")
            null
        }
        return null
    }*/
        return listOf()
    }
}



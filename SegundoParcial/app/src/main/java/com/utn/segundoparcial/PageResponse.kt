package com.utn.segundoparcial

import com.google.gson.annotations.SerializedName
import com.utn.segundoparcial.domain.models.Movie

data class PageResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: MutableList<ResultApi>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

data class ResultApi(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("id") val id: Int,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genre_ids") val genres: MutableList<Int>,
    @SerializedName("original_title") val title: String,
    @SerializedName("original_language") val language: String,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)
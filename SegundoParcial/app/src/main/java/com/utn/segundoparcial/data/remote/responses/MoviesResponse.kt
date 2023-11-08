package com.utn.segundoparcial.data.remote.responses

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
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

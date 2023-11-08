package com.utn.segundoparcial.data.models

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("id") val movieId : Int,
    @SerializedName("genres") val genres: List<Gender>,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("original_title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Double
)

data class Gender(
    @SerializedName("name") val name: String
)
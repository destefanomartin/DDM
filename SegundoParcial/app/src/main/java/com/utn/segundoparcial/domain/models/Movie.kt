package com.utn.segundoparcial.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val genres: List<Int>,
    val movieId: Int,
    val origLanguage: String,
    val origTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int
)
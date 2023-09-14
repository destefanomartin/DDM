package com.utn.primerparcial.movies.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class Movie(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "director")
    var director: String,

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "year")
    var year: Int,

    @ColumnInfo(name = "rating")
    var rating: Int,

    @ColumnInfo(name = "image")
    var image: String,

) : Parcelable


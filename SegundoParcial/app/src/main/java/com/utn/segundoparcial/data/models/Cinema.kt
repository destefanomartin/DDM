package com.utn.segundoparcial.data.models

import com.google.firebase.firestore.GeoPoint

data class Cinema (
    val name : String,
    val address : String,
    val location : GeoPoint,
    val schedules : String,
    val homepage: String,
    var distance : Int
)

{
    constructor() : this("", "", GeoPoint(0.0, 0.0), "", "", 0)
}
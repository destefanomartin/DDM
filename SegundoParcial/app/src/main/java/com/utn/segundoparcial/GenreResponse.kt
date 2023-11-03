package com.utn.segundoparcial

import com.google.gson.annotations.SerializedName

class GenreResponse (

    @SerializedName("genres") val genres : MutableList<Int>
)
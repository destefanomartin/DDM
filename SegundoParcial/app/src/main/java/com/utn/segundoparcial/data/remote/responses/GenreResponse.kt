package com.utn.segundoparcial.data.remote.responses

import com.google.gson.annotations.SerializedName

class GenreResponse (

    @SerializedName("genres") val genres : MutableList<Int>
)
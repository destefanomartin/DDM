package com.utn.primerparcial.login.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User (
    val name : String, val password : String, val email : String, val image : String, val age : Int
) : Parcelable
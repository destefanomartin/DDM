package com.utn.primerparcial.login.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
class User (

    @PrimaryKey(autoGenerate = true)
    var id : Int,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "age")
    var age : Int,

    @ColumnInfo(name = "favMovie")
    var favMovie : String

) : Parcelable
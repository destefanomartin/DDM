package com.utn.segundoparcial.ui.viewmodels

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Build
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.google.firebase.firestore.GeoPoint
import com.utn.segundoparcial.data.models.Cinema
import com.utn.segundoparcial.data.models.Movie
import com.utn.segundoparcial.data.remote.FirebaseDataSource
import com.utn.segundoparcial.data.repository.PagesRepository
import com.utn.segundoparcial.data.repository.PagesRepositoryImpl
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
class LocationViewModel : ViewModel() {

    var location : Location? = null
    val PERMISSION_ID = 42
    lateinit var mFusedLocationClient: FusedLocationProviderClient

    private var _cinemas = MutableLiveData<MutableList<Cinema>>()
    val cinemasList: LiveData<MutableList<Cinema>>
        get() = _cinemas

    fun getCinemas (){
        viewModelScope.launch {
            try {
                val cinemasWithoutDistance = FirebaseDataSource().getCinemas()
                for(item in cinemasWithoutDistance)
                    item.distance = calculateDistance(item.location)
                _cinemas.value = cinemasWithoutDistance.sortedBy { it.distance } as MutableList<Cinema>
            }
        catch (e: Exception) {
            Log.d("LocationViewModel", "Error: ${e.message}")
        }
        }
    }

    private fun calculateDistance(location : GeoPoint) : Int
    {
        val loc1 = Location("")
        loc1.latitude = location.latitude
        loc1.longitude = location.longitude
        val loc2 = Location("")
        loc2.latitude = this.location!!.latitude
        loc2.longitude = this.location!!.longitude
        val distanceInMeters = loc1.distanceTo(loc2)
        return distanceInMeters.toInt()
    }

}
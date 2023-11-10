package com.utn.segundoparcial.ui.fragments

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
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.utn.segundoparcial.R
import com.utn.segundoparcial.ui.adapters.CinemasAdapter
import com.utn.segundoparcial.ui.viewmodels.LocationViewModel
import kotlinx.coroutines.launch

class LocationFragment : Fragment() {

    companion object {
        fun newInstance() = LocationFragment()
    }
    private val locationViewModel: LocationViewModel by viewModels()
    private lateinit var rvCinemas : RecyclerView
    private lateinit var v : View
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationViewModel.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        getLastLocation()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_location, container, false)
        rvCinemas = v.findViewById(R.id.rvCinemas)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationViewModel.getCinemas()
        locationViewModel.cinemasList.observe(viewLifecycleOwner) { cinemas ->
                val cinemaAdapter = CinemasAdapter(cinemas)
            rvCinemas.adapter = cinemaAdapter
        }



    }

    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {


                locationViewModel.mFusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                    locationViewModel.location = task.result
                    if (locationViewModel.location == null) {
                        requestNewLocationData()
                    } else {

                        Log.d ("Test", locationViewModel.location!!.latitude.toString())
                        Log.d ("Test",locationViewModel.location!!.longitude.toString())

//                        findViewById<TextView>(R.id.latTextView).text = location.latitude.toString()
//                        findViewById<TextView>(R.id.lonTextView).text = location.longitude.toString()
                    }
                }


            } else {
                Toast.makeText(requireActivity(), "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

            @RequiresApi(Build.VERSION_CODES.S)
            @SuppressLint("MissingPermission")
            fun requestNewLocationData() {
                val mLocationRequest = com.google.android.gms.location.LocationRequest.Builder(10000)
                    .setPriority(PRIORITY_HIGH_ACCURACY)
                    .setDurationMillis(10000)
                    .build()

                locationViewModel.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
                locationViewModel.mFusedLocationClient.requestLocationUpdates(
                    mLocationRequest, mLocationCallback, Looper.getMainLooper()
                )

            }

        private val mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                var mLastLocation: Location? = locationResult.lastLocation
                Log.d ("Test",mLastLocation?.latitude.toString())
                Log.d ("Test",mLastLocation?.longitude.toString())
        //            findViewById<TextView>(R.id.latTextView).text = mLastLocation.latitude.toString()
        //            findViewById<TextView>(R.id.lonTextView).text = mLastLocation.longitude.toString()
            }
        }

        fun isLocationEnabled(): Boolean {
            var locationManager: LocationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )
        }

        fun checkPermissions(): Boolean {
            if (ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                return true
            }
            return false
        }

        @RequiresApi(Build.VERSION_CODES.S)
        private fun requestPermissions() {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
                locationViewModel.PERMISSION_ID
            )
        }


        @RequiresApi(Build.VERSION_CODES.S)
        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
            if (requestCode == locationViewModel.PERMISSION_ID) {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getLastLocation()
                }
            }
        }
}



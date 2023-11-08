package com.utn.segundoparcial.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.utn.segundoparcial.data.remote.FirebaseDataSource
import com.utn.segundoparcial.data.models.MovieDetails
import kotlinx.coroutines.launch

class WatchlistViewModel : ViewModel() {
    private val user = FirebaseAuth.getInstance().currentUser
    private var _movies = MutableLiveData<MutableList<MovieDetails>>()
    val moviesList: LiveData<MutableList<MovieDetails>>
        get() = _movies


    fun getWatchlist() {

        viewModelScope.launch {

            try {
                _movies.value = FirebaseDataSource().getMovies(user!!.uid)
            } catch (e: Exception) {
                Log.d("tag", "${e.message}")

            }
        }

    }
}
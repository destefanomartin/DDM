package com.utn.segundoparcial.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.utn.segundoparcial.data.remote.FirebaseDataSource
import com.utn.segundoparcial.data.models.MovieDetails
import com.utn.segundoparcial.data.repository.PagesRepository
import com.utn.segundoparcial.data.repository.PagesRepositoryImpl
import com.utn.segundoparcial.ui.fragments.DetailsFragment
import kotlinx.coroutines.launch


class DetailsViewModel : ViewModel() {

    val currentUser = FirebaseAuth.getInstance().currentUser

    var moviesRepository: PagesRepository = PagesRepositoryImpl()

    private var _movieExists = MutableLiveData<Boolean>()
    val movieExists: LiveData<Boolean> get() = _movieExists
    private var _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> get() = _movieDetails

    private var _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> get() = _status



    fun deleteMovie(uid: String, movie: MovieDetails) {
        viewModelScope.launch {
            FirebaseDataSource().deleteMovie(uid,movie)
        }
    }
    fun saveMovie(uid: String, movie: MovieDetails) {
        viewModelScope.launch {
            FirebaseDataSource().saveMovie(uid,movie)
        }
    }
    fun getDetails(id: Int) {
        _status.value = ApiStatus.LOADING
        viewModelScope.launch {
            try {
                _movieDetails.value = moviesRepository.getMoviesById(id)
                _movieExists.value = FirebaseDataSource().movieExistsCheckById(currentUser!!.uid,id)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = ApiStatus.ERROR
            }
        }
    }


}
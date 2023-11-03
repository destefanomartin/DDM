package com.utn.segundoparcial.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utn.segundoparcial.domain.models.Movie
import com.utn.segundoparcial.domain.repository.PagesRepository
import com.utn.segundoparcial.domain.repository.PagesRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {

    val _movies = MutableLiveData<List<Movie>?>()
    var movies: MutableList<Movie>? = null
    private val moviesRepository : PagesRepository = PagesRepositoryImpl()

    fun getMoviesForRecyclerView() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                movies = moviesRepository.getPage(1)
                Log.d("MovieListViewModel", "Movies: $movies")

            } catch (e: Exception) {
                Log.d("MovieListViewModel", "Error: ${e.message}")
            }
        }
    }


}
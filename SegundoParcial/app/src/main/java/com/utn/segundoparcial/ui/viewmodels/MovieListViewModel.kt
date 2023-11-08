package com.utn.segundoparcial.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utn.segundoparcial.data.models.Movie
import com.utn.segundoparcial.data.repository.PagesRepository
import com.utn.segundoparcial.data.repository.PagesRepositoryImpl
import kotlinx.coroutines.launch

enum class ApiStatus {LOADING, ERROR, DONE}

class MovieListViewModel : ViewModel() {

    private var _movies = MutableLiveData<MutableList<Movie>>()
    private val moviesRepository : PagesRepository = PagesRepositoryImpl()
    var page = 1
    val moviesList: LiveData<MutableList<Movie>>
        get() = _movies

    private var _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status
    init {
        getMoviesForRecyclerView(1)
    }
    fun getMoviesForRecyclerView(page : Int) {
        _status.value = ApiStatus.LOADING
        viewModelScope.launch {
            try {
                if(page == 1)
                    _movies.value = moviesRepository.getPage(1)
                else
                    _movies.value?.addAll(moviesRepository.getPage(page))
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                Log.d("MovieListViewModel", "Error: ${e.message}")
                _status.value = ApiStatus.ERROR
            }

        }
    }


}
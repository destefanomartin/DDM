package com.utn.segundoparcial.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utn.segundoparcial.ui.adapters.MoviesAdapter
import com.utn.segundoparcial.R
import com.utn.segundoparcial.ui.viewmodels.MovieListViewModel

class MovieListFragment : Fragment() {


    companion object {
        fun newInstance() = MovieListFragment()
    }

    private val movieListViewModel: MovieListViewModel by viewModels()
    private lateinit var v: View
    private lateinit var rvMovies: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_movie_list, container, false)
        rvMovies = v.findViewById(R.id.rvMovies)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            movieListViewModel.getMoviesForRecyclerView()
            setupRecyclerView()

        }



    private fun setupRecyclerView() {
        val allMovies = movieListViewModel.movies
        val moviesAdapter = MoviesAdapter(allMovies)
        { movie ->
            val action = MovieListFragmentDirections.actionMovieListFragment2ToDetailsFragment()
            findNavController().navigate(action)
        }
        with(rvMovies) {
            adapter = moviesAdapter
            LinearLayoutManager(context)
        }
    }
}






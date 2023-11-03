package com.utn.primerparcial.movies.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.utn.primerparcial.R
import com.utn.primerparcial.movies.adapters.MoviesAdapter
import com.utn.primerparcial.movies.database.AppDatabase
import com.utn.primerparcial.movies.database.MovieDao

class MovieListFragment : Fragment() {


    companion object {
        fun newInstance() = MovieListFragment()
    }

    lateinit var v : View
    private var moviedb : AppDatabase? = null
    private var MovieDao : MovieDao? = null
    private lateinit var rvMovies: RecyclerView
    private lateinit var addButton : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_movie_list, container, false)

        addButton = v.findViewById(R.id.addButton)
        rvMovies = v.findViewById(R.id.rvMovies)


        return v
    }

    override fun onStart() {
        super.onStart()

        moviedb = AppDatabase.getInstance(v.context)
        MovieDao = moviedb?.movieDao()

        MovieDao?.fetchAllMovies()

        setupRecyclerView()

        addButton.setOnClickListener {
            val action = MovieListFragmentDirections.actionMovieListFragmentToCreateMovieFragment()
            findNavController().navigate(action)
        }

    }

    private fun setupRecyclerView() {
        val allMovies = moviedb!!.movieDao()!!.getAllMovies()
        val moviesAdapter = MoviesAdapter(allMovies) {movie ->
        val action = MovieListFragmentDirections.actionMovieListFragmentToDetailsFragment(movie)
        findNavController().navigate(action)
        }
        with(rvMovies) {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(context)
        }


    }

}
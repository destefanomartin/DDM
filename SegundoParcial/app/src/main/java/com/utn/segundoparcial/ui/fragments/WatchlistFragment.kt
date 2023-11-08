package com.utn.segundoparcial.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.utn.segundoparcial.R
import com.utn.segundoparcial.ui.viewmodels.WatchlistViewModel
import com.utn.segundoparcial.data.models.toMovie
import com.utn.segundoparcial.ui.adapters.MoviesAdapter

class WatchlistFragment : Fragment() {



    private lateinit var rvMoviesWatchlist: RecyclerView
    companion object {
        fun newInstance() = WatchlistFragment()
    }
    private lateinit var v : View

    private val watchlistViewModel: WatchlistViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_watchlist, container, false)
        rvMoviesWatchlist = v.findViewById(R.id.rvMoviesWatchlist)
        return v

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        watchlistViewModel.getWatchlist()
        watchlistViewModel.moviesList.observe(viewLifecycleOwner) {movieList ->
            val moviesAdapter = MoviesAdapter(movieList.map { it.toMovie() }.toMutableList())
            { movie ->
                val action = WatchlistFragmentDirections.actionWatchlistFragment2ToDetailsFragment(movie.movieId)
                findNavController().navigate(action)
            }
            rvMoviesWatchlist.adapter = moviesAdapter

        }
    }

}



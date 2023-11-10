package com.utn.segundoparcial.ui.fragments

import android.os.Bundle
import android.util.Log
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
import com.utn.segundoparcial.databinding.FragmentMovieListBinding
import com.utn.segundoparcial.ui.viewmodels.ApiStatus
import com.utn.segundoparcial.ui.viewmodels.MovieListViewModel

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null

    private val binding get() = _binding!!


    private val movieListViewModel: MovieListViewModel by viewModels()
    private lateinit var v: View
    private lateinit var rvMovies: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(layoutInflater, container, false)
        v = binding.root
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMovies = binding.rvMovies
        movieListViewModel.moviesList.observe(viewLifecycleOwner) { movies ->
                val moviesAdapter = MoviesAdapter(movies)
                { movie ->
                    val action = MovieListFragmentDirections.actionMovieListFragment2ToDetailsFragment(movie.movieId)
                    findNavController().navigate(action)
                }
            rvMovies.adapter = moviesAdapter

        }
        observeApiStatus()
        nextPageMovies()
    }

    private fun observeApiStatus() {
        movieListViewModel.status.observe(viewLifecycleOwner) { status->
            when (status) {
                ApiStatus.LOADING -> {
                    binding.statusOffline.visibility = View.GONE
                    if (movieListViewModel.page == 1) {
                        binding.shimmerLoading.visibility = View.VISIBLE
                        binding.rvMovies.visibility =View.GONE
                    }
                }
                ApiStatus.ERROR -> {
                    binding.statusOffline.visibility = View.VISIBLE
                    binding.shimmerLoading.visibility = View.GONE
                    binding.rvMovies.visibility =View.GONE
                }
                ApiStatus.DONE -> {
                    binding.statusOffline.visibility = View.GONE
                    binding.shimmerLoading.visibility = View.GONE
                    binding.rvMovies.visibility =View.VISIBLE
                }
            }
        }
    }

    private fun nextPageMovies() {
        rvMovies.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val visibleItemCount = recyclerView.layoutManager!!.childCount
                    val totalItemCount = recyclerView.layoutManager!!.itemCount

                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition()

                    if (movieListViewModel.status.value != ApiStatus.LOADING) {
                        if ((visibleItemCount + lastVisiblePosition) >= totalItemCount - 6) {
                            if (movieListViewModel.page < 100) {
                                movieListViewModel.page++
                            }
                            //Log.d("tag", "${viewModel.page}")
                            movieListViewModel.getMoviesForRecyclerView(movieListViewModel.page)
                        }
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}












package com.utn.segundoparcial.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.utn.segundoparcial.R
import com.utn.segundoparcial.databinding.FragmentDetailsBinding
import com.utn.segundoparcial.ui.viewmodels.ApiStatus
import com.utn.segundoparcial.ui.viewmodels.DetailsViewModel
import com.utn.segundoparcial.ui.viewmodels.MovieListViewModel
import kotlin.math.roundToInt

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    private val detailsViewModel: DetailsViewModel by viewModels()
    private lateinit var movieViewModel : MovieListViewModel
    private lateinit var v : View
    private lateinit var backImage : ImageView
    private lateinit var poster : ImageView
    private lateinit var title : TextView
    private lateinit var year : TextView
    private lateinit var genre : TextView
    private lateinit var runtime : TextView
    private lateinit var description : TextView
    private lateinit var message : TextView
    private lateinit var rating : RatingBar
    private lateinit var textRating : TextView
    private lateinit var addToUser : FloatingActionButton
    private lateinit var deleteButton : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idMovie = it.getInt("movieId")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        v = binding.root
        return v


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        poster = v.findViewById(R.id.moviePoster)
        backImage = v.findViewById(R.id.backdropImage)
        title = v.findViewById(R.id.movieName)
        year = v.findViewById(R.id.movieReleaseDate)
        genre = v.findViewById(R.id.movieGenre)
        message = v.findViewById(R.id.movieGenreMessage)
        runtime = v.findViewById(R.id.movieRuntime)
        description = v.findViewById(R.id.movieDescription)
        rating = v.findViewById(R.id.ratingBar)
        textRating = v.findViewById(R.id.ratingText)
        addToUser = v.findViewById(R.id.saveButton)
        deleteButton = v.findViewById(R.id.deleteButton)


        detailsViewModel.getDetails(idMovie)

        detailsViewModel.movieExists.observe(viewLifecycleOwner){
            if(it)
            {
                addToUser.hide()
                deleteButton.show()
            }
            else
            {
                addToUser.show()
                deleteButton.hide()
            }
        }
        princObserver()
        observeStatus()

        deleteButton.setOnClickListener(){
            detailsViewModel.deleteMovie(detailsViewModel.currentUser!!.uid,detailsViewModel.movieDetails.value!!)
        }

        addToUser.setOnClickListener(){
            detailsViewModel.saveMovie(detailsViewModel.currentUser!!.uid,detailsViewModel.movieDetails.value!!)
        }
    }

    private fun observeStatus() {
        detailsViewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                ApiStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.backdropImage.visibility = View.GONE
                    binding.nestedScrollView.visibility = View.GONE
                    binding.statusOffline.visibility = View.GONE
                }
                ApiStatus.DONE -> {
                    binding.progressBar.visibility = View.GONE
                    binding.backdropImage.visibility = View.VISIBLE
                    binding.nestedScrollView.visibility = View.VISIBLE
                    binding.statusOffline.visibility = View.GONE
                }
                ApiStatus.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.backdropImage.visibility = View.GONE
                    binding.nestedScrollView.visibility = View.GONE
                    binding.statusOffline.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun princObserver()
    {
        detailsViewModel.movieDetails.observe(viewLifecycleOwner) {
            title.text = it.title
            year.text = it.releaseDate.substring(0,4)
            genre.text = it.genders
            runtime.text = it.runtime
            description.text = it.overview
            rating.rating = ((it.voteAverage)*5.0/10.0).toFloat()
            textRating.text = String.format("%.2f",(it.voteAverage*5.0/10.0))
            Glide.with(v.context)
                .load(it.posterPath)
                .into(poster)
            Glide.with(v.context).load(it.backdropPath)
                .into(backImage)
        }
    }
    companion object {
        var idMovie = 1
    }
}


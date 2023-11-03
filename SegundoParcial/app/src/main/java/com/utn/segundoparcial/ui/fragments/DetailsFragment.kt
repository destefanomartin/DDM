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
import com.utn.segundoparcial.R
import com.utn.segundoparcial.ui.viewmodels.DetailsViewModel

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel
    private lateinit var v : View
    private lateinit var poster : ImageView
    private lateinit var title : TextView
    private lateinit var year : TextView
    private lateinit var genre : TextView
    private lateinit var director : TextView
    private lateinit var description : TextView
    private lateinit var message : TextView
    private lateinit var rating : RatingBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_details, container, false)
        poster = v.findViewById(R.id.posterLogo)
        title = v.findViewById(R.id.movieName)
        year = v.findViewById(R.id.movieDirectorMessage)
        genre = v.findViewById(R.id.movieGenre)
        message = v.findViewById(R.id.movieGenreMessage)
        director = v.findViewById(R.id.movieDirector)
        description = v.findViewById(R.id.movieDescription)
        rating = v.findViewById(R.id.ratingBar)
        return v


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
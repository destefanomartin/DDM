package com.utn.primerparcial.movies.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.utn.primerparcial.R

class detailsFragment : Fragment() {


    lateinit var v : View
    lateinit var title : TextView
    lateinit var director : TextView
    lateinit var genre : TextView
    lateinit var year : TextView
    lateinit var rating : TextView
    lateinit var image : ImageView
    companion object {
        fun newInstance() = detailsFragment()
    }

    private val args: detailsFragmentArgs by navArgs()

    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_details, container, false)
        return v

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image = v.findViewById(R.id.movieImage)
        title = v.findViewById(R.id.movieTitleText)
        director = v.findViewById(R.id.movieDirectorText)
        genre = v.findViewById(R.id.movieGenreText)
        year = v.findViewById(R.id.movieYearText)
        rating = v.findViewById(R.id.movieRatingText)


        Glide.with(image)
            .load(args.movies.image)
            .into(image)

        title.text = args.movies.name
        director.text = args.movies.director
        genre.text = args.movies.genre
        year.text = args.movies.year.toString()
        rating.text = args.movies.rating.toString()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
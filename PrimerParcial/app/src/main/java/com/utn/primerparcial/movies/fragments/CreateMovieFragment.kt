package com.utn.primerparcial.movies.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.utn.primerparcial.R

class CreateMovieFragment : Fragment() {

    companion object {
        fun newInstance() = CreateMovieFragment()
    }

    lateinit var v : View
    lateinit var title : TextInputEditText
    lateinit var director : TextInputEditText
    lateinit var genre : TextInputEditText
    lateinit var year : TextInputEditText
    lateinit var rating : TextInputEditText




    private lateinit var viewModel: CreateMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = v.findViewById(R.id.inputMovieTitleEditText)
        genre = v.findViewById(R.id.inputMovieGenreEditText)
        year =  v.findViewById(R.id.inputMovieYearEditText)
        director = v.findViewById(R.id.inputMovieDirectorEditText)
        rating = v.findViewById(R.id.inputMovieRatingEditText)



    }

}
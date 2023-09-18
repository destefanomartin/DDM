package com.utn.primerparcial.movies.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.utn.primerparcial.R
import com.utn.primerparcial.movies.database.AppDatabase
import com.utn.primerparcial.movies.database.MovieDao
import com.utn.primerparcial.movies.models.Movie

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
    lateinit var image : TextInputEditText

    lateinit var insertButton : Button



    private lateinit var viewModel: CreateMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_create_movie, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = v.findViewById(R.id.inputMovieTitleEditText)
        genre = v.findViewById(R.id.inputMovieGenreEditText)
        year =  v.findViewById(R.id.inputMovieYearEditText)
        director = v.findViewById(R.id.inputMovieDirectorEditText)
        rating = v.findViewById(R.id.inputMovieRatingEditText)
        insertButton = v.findViewById(R.id.insertButton)
        image = v.findViewById(R.id.inputMovieImageEditText)



        insertButton.setOnClickListener {
            AppDatabase.getInstance(requireContext())?.movieDao()?.insertMovie(
                Movie(0,
                    title.text.toString(),
                    director.text.toString(),
                    genre.text.toString(),
                    year.text.toString().toInt(),
                    rating.text.toString().toDouble(),
                    image.text.toString()
                )
            )
            requireActivity().onBackPressed()
        }



    }

}
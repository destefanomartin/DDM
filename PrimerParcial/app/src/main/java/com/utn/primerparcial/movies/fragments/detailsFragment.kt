package com.utn.primerparcial.movies.fragments

import android.content.res.ColorStateList
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.InputType.TYPE_CLASS_NUMBER
import android.text.InputType.TYPE_CLASS_TEXT
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.utn.primerparcial.R

class detailsFragment : Fragment() {


    lateinit var v : View
    lateinit var title : EditText
    lateinit var director : EditText
    lateinit var genre : EditText
    lateinit var year : EditText
    lateinit var rating : EditText
    lateinit var image : ImageView
    lateinit var editButton : Button
    lateinit var saveButton : Button
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
        editButton = v.findViewById(R.id.editButton)
        saveButton = v.findViewById(R.id.saveButton)

        Glide.with(image)
            .load(args.movies.image)
            .into(image)

        saveButton.visibility = View.INVISIBLE

        title.setText(args.movies.name)
        director.setText(args.movies.director)
        genre.setText(args.movies.genre)
        year.setText(args.movies.year.toString())
        rating.setText(args.movies.rating.toString())

        editButton.setOnClickListener(){
            title.isEnabled = true
            director.isEnabled = true
            genre.isEnabled = true
            year.isEnabled = true
            rating.isEnabled = true
            saveButton.visibility = View.VISIBLE
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
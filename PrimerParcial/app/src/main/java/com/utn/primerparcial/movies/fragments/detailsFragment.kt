package com.utn.primerparcial.movies.fragments

import android.content.res.ColorStateList
import android.graphics.Color
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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.utn.primerparcial.R
import com.utn.primerparcial.movies.database.AppDatabase
import com.utn.primerparcial.movies.database.MovieDao
import com.utn.primerparcial.movies.models.Movie

class detailsFragment : Fragment() {


    lateinit var v : View
    lateinit var title : EditText
    lateinit var director : EditText
    lateinit var genre : EditText
    lateinit var year : EditText
    lateinit var rating : EditText
    lateinit var image : ImageView
    lateinit var editButton : FloatingActionButton
    lateinit var saveButton : FloatingActionButton
    lateinit var deleteButton : FloatingActionButton
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
        deleteButton = v.findViewById(R.id.deleteButton)
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
            editButton.visibility = View.INVISIBLE
            deleteButton.visibility = View.INVISIBLE
            saveButton.visibility = View.VISIBLE
        }

        saveButton.setOnClickListener(){
            title.isEnabled = false
            director.isEnabled = false
            genre.isEnabled = false
            year.isEnabled = false
            rating.isEnabled = false
            saveButton.visibility = View.INVISIBLE
            deleteButton.visibility = View.VISIBLE
            editButton.visibility = View.VISIBLE
            AppDatabase.getInstance(v.context)
                ?.movieDao()
                ?.updateMovie(Movie(args.movies.id, title.text.toString(), director.text.toString(), genre.text.toString(), year.text.toString().toInt(), rating.text.toString().toDouble(), args.movies.image))
            Snackbar.make(v, "Elemento actualizado", Snackbar.LENGTH_SHORT)
                .setBackgroundTint(Color.BLACK)
                .setTextColor(Color.WHITE)
                .show()
            requireActivity().onBackPressed()
        }

        deleteButton.setOnClickListener(){
            AppDatabase.getInstance(v.context)
                ?.movieDao()
                ?.delete(args.movies)
            Snackbar.make(v, "Elemento eliminado", Snackbar.LENGTH_SHORT)
                .setBackgroundTint(Color.BLACK)
                .setTextColor(Color.WHITE)
                .show()
            requireActivity().onBackPressed()
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
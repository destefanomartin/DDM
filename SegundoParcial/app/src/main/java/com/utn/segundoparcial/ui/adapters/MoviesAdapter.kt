package com.utn.segundoparcial.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.utn.segundoparcial.domain.models.Movie
import com.utn.segundoparcial.R

class MoviesAdapter(private val list: MutableList<Movie>?, private val onItemClick: (Movie) -> Unit) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = list?.get(position)
        holder.bind(movie!!)
        holder.setOnClickListener { onItemClick(movie) }

    }

    inner class MoviesViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(movie: Movie) {
            val imgMoviePoster: ImageView = v.findViewById(R.id.movieImage)
            Log.d("URL", movie.posterPath)
            Glide.with(imgMoviePoster)
                .load(movie.posterPath)
                .into(imgMoviePoster)



        }
        fun setOnClickListener(onClick: () -> Unit) {
            v.setOnClickListener {
                onClick()
            }
        }
    }
}

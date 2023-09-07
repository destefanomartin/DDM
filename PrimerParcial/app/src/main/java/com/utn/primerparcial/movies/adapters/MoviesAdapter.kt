package com.utn.primerparcial.movies.adapters

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.utn.primerparcial.R
import com.utn.primerparcial.movies.models.Movie

class MoviesAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = list[position]
        holder.bind(movie)
        holder.setOnClickListener { onItemClick(movie) }

    }

    inner class MoviesViewHolder (private val v : View) : RecyclerView.ViewHolder(v) {
        fun bind(movie : Movie)
        {
            val txtMovieName : TextView = v.findViewById(R.id.movieTitle)
            txtMovieName.text = movie.name

            val imgMoviePoster : ImageView = v.findViewById(R.id.movieImage)
            Glide.with(imgMoviePoster)
                .load(movie.image)
                .centerCrop()
                .into(imgMoviePoster)
}

package com.utn.primerparcial.movies.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.utn.primerparcial.R
import com.utn.primerparcial.movies.models.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class StartingMovies (private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("StartingMovies", "Pre-populating database...")
            fillWithStartingMovies(context)
        }
    }

    /**
     * Pre-populate database with hard-coded movies
     */
    private fun fillWithStartingMovies(context: Context) {
        val movies = listOf(
            Movie(1, "The Godfather", "Francis Ford Coppola", "Drama", 1972, 9, "https://i.pinimg.com/originals/c8/51/cd/c851cd6600ed7536f270db9e14319e8f.jpg"),
            Movie(2, "The Godfather: Part II", "Francis Ford Coppola", "Drama", 1974, 9, "https://i.pinimg.com/originals/6a/6e/9a/6a6e9a1b6b0b0b0b0b0b0b0b0b0b0b0b.jpg"),
            Movie(3, "The Batman", "Matt Reeves", "Action", 2022, 0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzTtJdgjRlsqyV-GXfD9HSnqBi6O8hjyqqQHqYYos&s"),
            Movie(4, "The Dark Knight", "Christopher Nolan", "Action", 2008, 9, "https://i.pinimg.com/originals/6a/6e/9a/6a6e9a1b6b0b0b0b0b0b0b0b0b0b0b0b.jpg"),
            )
        val dao = AppDatabase.getInstance(context)?.movieDao()

        movies.forEach {
            dao?.insertMovie(it)
        }
    }

    /**
     * Pre-populate database with users from a Json file
     */
    private fun fillWithStartingUsersFromJson(context: Context) {
        val dao = AppDatabase.getInstance(context)?.movieDao()

        try {
            val movies = loadJSONArray(context, R.raw.movies)
            for (i in 0 until movies.length()) {
                val item = movies.getJSONObject(i)
                val movie = Movie(
                    item.getInt("id"),
                    item.getString("name"),
                    item.getString("director"),
                    item.getString("genre"),
                    item.getInt("year"),
                    item.getInt("rating"),
                    item.getString("image")
                )

                dao?.insertMovie(movie)
            }
        } catch (e: JSONException) {
            Log.e("fillWithStartingNotes", e.toString())
        }
    }

    /**
     * Utility to load a JSON array from the raw folder
     */
    private fun loadJSONArray(context: Context, file: Int): JSONArray {
        val inputStream = context.resources.openRawResource(file)

        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}
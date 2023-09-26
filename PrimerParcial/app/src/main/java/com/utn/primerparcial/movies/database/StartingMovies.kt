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

    private fun fillWithStartingMovies(context: Context) {
        val movies = listOf(
            Movie(0, "The Godfather", "Francis Ford Coppola", "Drama", 1972, 9.0, "https://i.pinimg.com/originals/c8/51/cd/c851cd6600ed7536f270db9e14319e8f.jpg"),
            Movie(0, "The Godfather: Part II", "Francis Ford Coppola", "Drama", 1974, 9.3, "https://posterspy.com/wp-content/uploads/2022/04/Godfather2Container.jpg"),
            Movie(0, "The Batman", "Matt Reeves", "Action", 2022, 4.3, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzTtJdgjRlsqyV-GXfD9HSnqBi6O8hjyqqQHqYYos&s"),
            Movie(0, "The Dark Knight", "Christopher Nolan", "Action", 2008, 5.6, "https://cdn.europosters.eu/image/750/posters/batman-dark-knight-joker-i116354.jpg"),
            Movie(0, "The Dark Knight Rises", "Christopher Nolan", "Action", 2012, 7.8, "https://m.media-amazon.com/images/M/MV5BMTk4ODQzNDY3Ml5BMl5BanBnXkFtZTcwODA0NTM4Nw@@._V1_.jpg"),
            Movie(0, "The Godfather: Part III", "Francis Ford Coppola", "Drama", 1990, 6.7, "https://www.themoviedb.org/t/p/original/lm3pQ2QoQ16pextRsmnUbG2onES.jpg"),
            Movie(0, "Patch Adams", "Tom Shadyac", "Comedy", 1998, 6.8, "https://i.pinimg.com/originals/11/f4/f5/11f4f51ef3f35bae716246f232d502a6.jpg"),
            Movie(0, "The Pursuit of Happyness", "Gabriele Muccino", "Drama", 2006, 8.0, "https://www.themoviedb.org/t/p/original/f6l9rghSHORkWLurUGJhaKAiyjY.jpg"),
            Movie(0, "The Wolf of Wall Street", "Martin Scorsese", "Comedy", 2013, 8.2, "https://m.media-amazon.com/images/M/MV5BMjIxMjgxNTk0MF5BMl5BanBnXkFtZTgwNjIyOTg2MDE@._V1_.jpg"),
            Movie(0, "The Shawshank Redemption", "Frank Darabont", "Drama", 1994, 9.3, "https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_FMjpg_UX1000_.jpg"),
            Movie(0, "Shrek", "Andrew Adamson", "Comedy", 2001, 7.8, "https://m.media-amazon.com/images/I/71HPEO8IJTL.jpg"),
            )
        val dao = AppDatabase.getInstance(context)?.movieDao()

        movies.forEach {
            dao?.insertMovie(it)
        }
    }


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
                    item.getDouble("rating"),
                    item.getString("image")
                )

                dao?.insertMovie(movie)
            }
        } catch (e: JSONException) {
            Log.e("fillWithStartingNotes", e.toString())
        }
    }


    private fun loadJSONArray(context: Context, file: Int): JSONArray {
        val inputStream = context.resources.openRawResource(file)

        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}
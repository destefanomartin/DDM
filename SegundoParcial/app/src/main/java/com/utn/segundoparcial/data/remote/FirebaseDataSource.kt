package com.utn.segundoparcial.data.remote

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.utn.segundoparcial.data.models.Cinema
import com.utn.segundoparcial.data.models.MovieDetails
import com.utn.segundoparcial.data.models.User
import kotlinx.coroutines.tasks.await

class FirebaseDataSource {

    val db = Firebase.firestore

    suspend fun addUser(uid: String, name: String, email: String, country: String){
        val user = hashMapOf(
            "name" to name,
            "email" to email,
            "country" to country
        )

        db.collection("Users").document(uid).set(user).await()

    }

    suspend fun getUser(uid: String): User? {
        val user = db.collection("Users").document(uid).get().await()
        return user.toObject<User>()
    }

    suspend fun saveMovie(uid: String, movieToAdd : MovieDetails) {
        val user = db.collection("Users").document(uid)
        user.update("films", FieldValue.arrayUnion(movieToAdd))
    }

    suspend fun getMovies(uid: String): MutableList<MovieDetails>? {
        val user = db.collection("Users").document(uid).get().await()
        return user.toObject<User>()?.films
    }

    suspend fun deleteMovie(uid: String, movieToDelete : MovieDetails) {
        val user = db.collection("Users").document(uid)
        user.update("films", FieldValue.arrayRemove(movieToDelete))
    }

    suspend fun movieExistsCheckById(uid: String, id: Int): Boolean {
        val user = db.collection("Users").document(uid).get().await()
        val movies = user.toObject<User>()?.films
        if (movies != null) {
            for (movie in movies) {
                if (movie.movieId == id) {
                    return true
                }
            }
        }
        return false
    }


    suspend fun getCinemas(): MutableList<Cinema> {
        val cinemas = db.collection("Cines").get().await()
        return cinemas.toObjects(Cinema::class.java)
    }
}
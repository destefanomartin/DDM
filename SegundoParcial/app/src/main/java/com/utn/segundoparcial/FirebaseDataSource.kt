package com.utn.segundoparcial

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseDataSource {

    val db = Firebase.firestore

    suspend fun addUser(uid: String, name: String, email: String) {
        val user = hashMapOf(
            "name" to name,
            "email" to email,
        )

        db.collection("Users").document(uid).set(user).await()

    }
}
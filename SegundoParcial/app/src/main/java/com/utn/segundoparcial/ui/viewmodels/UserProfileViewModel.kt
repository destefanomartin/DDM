package com.utn.segundoparcial.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.utn.segundoparcial.data.models.User
import com.utn.segundoparcial.data.remote.FirebaseDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class UserProfileViewModel : ViewModel() {
    val db = Firebase.firestore
    val auth = Firebase.auth
    val currentUser = auth.currentUser
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    fun getUserData(uid: String) {
        viewModelScope.launch {
            try {
                val documentSnapshot = withContext(Dispatchers.IO) {
                    db.collection("Users").document(uid).get().await()
                }
                val userData = documentSnapshot.toObject<User>()
                _user.postValue(userData)
            } catch (e: Exception) {
                // Maneja el error si es necesario
            }
        }
    }

    fun signOut() {
        auth.signOut()
    }

}
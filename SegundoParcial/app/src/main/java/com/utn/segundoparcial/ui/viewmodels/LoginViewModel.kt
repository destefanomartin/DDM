package com.utn.segundoparcial.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.utn.segundoparcial.AuthResult
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.Dispatchers

class LoginViewModel : ViewModel() {
    private val auth = Firebase.auth

    val authResultLiveData = MutableLiveData<AuthResult>()
    val _signInResult = MutableLiveData<AuthResult>()
    fun signIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                _signInResult.postValue(AuthResult.Success("Inicio de sesión exitoso"))
            } catch (e: Exception) {
                _signInResult.postValue(
                    AuthResult.Error(
                        e.localizedMessage ?: "Error de inicio de sesión"
                    )
                )
            }
        }
    }
}










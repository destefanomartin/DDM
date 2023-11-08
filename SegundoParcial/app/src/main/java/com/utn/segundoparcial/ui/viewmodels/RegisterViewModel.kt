package com.utn.segundoparcial.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.utn.segundoparcial.data.models.AuthResult
import com.utn.segundoparcial.data.remote.FirebaseDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RegisterViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    private val _signUpResult = MutableLiveData<AuthResult>()
    val signUpResult: LiveData<AuthResult> = _signUpResult

    fun signUp(email: String, password: String, confirmPassword: String, name: String, country: String) {
        if (password != confirmPassword) {
            _signUpResult.postValue(AuthResult.Error("Las contraseñas no coinciden"))
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                auth.createUserWithEmailAndPassword(email, password).await()
                auth.currentUser?.uid?.let { FirebaseDataSource().addUser(it, name, email, country) }
                _signUpResult.postValue(AuthResult.Success("Registro exitoso"))
            } catch (e: Exception) {
                _signUpResult.postValue(AuthResult.Error(e.localizedMessage ?: "Error de registro"))
            }
        }
    }
}
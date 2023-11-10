package com.utn.segundoparcial.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.utn.segundoparcial.data.models.AuthResult
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.Dispatchers

class LoginViewModel : ViewModel() {
    private val auth = Firebase.auth

    val _authResultLiveData = MutableLiveData<AuthResult>()
    private val _signInResult = MutableLiveData<AuthResult>()
    val signInResult: LiveData<AuthResult> = _signInResult

    fun signIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                if (auth.currentUser!!.isEmailVerified) {
                    _signInResult.postValue(AuthResult.Success("Inicio de sesión exitoso"))
                } else {
                    _signInResult.postValue(AuthResult.Error("Verifica tu correo electrónico"))
                }
            } catch (e: FirebaseAuthInvalidUserException) {
                // El usuario no está registrado
                _signInResult.postValue(AuthResult.Error("Usuario no registrado. Regístrate para crear una cuenta"))
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                // Las credenciales proporcionadas no son válidas
                _signInResult.postValue(AuthResult.Error("Credenciales no válidas. Verifica tu correo electrónico y contraseña"))
            } catch (e: Exception) {
                // Otros errores
                _signInResult.postValue(
                    AuthResult.Error(
                        e.localizedMessage ?: "Error de inicio de sesión"
                    )
                )
            }
        }
    }
}











package com.utn.primerparcial.login.fragments

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.utn.primerparcial.login.models.AuthState

class RegisterViewModel : ViewModel() {

    private lateinit var auth : FirebaseAuth

    companion object {
        private const val TAG = "RegisterViewModel"
    }

    private val _authState by lazy { MutableLiveData<AuthState>(AuthState.Idle) }
    val authState: LiveData<AuthState> = _authState
    fun handleSignUp(email: String, password: String, confirmPassword: String) {
        if (password != confirmPassword) {
            _authState.value = AuthState.AuthError("Password does not match")
            return
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i(TAG,"Email signup is successful")
                    _authState.value = AuthState.Success
                } else {
                    task.exception?.let {
                        Log.i(TAG,"Email signup failed with error ${it.localizedMessage}")
                        _authState.value = AuthState.AuthError(it.localizedMessage)
                    }
                }
            }
    }
}

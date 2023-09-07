package com.utn.primerparcial.login.fragments

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.utn.primerparcial.R

class LoginFragment : Fragment() {


    lateinit var v: View
    lateinit var welcomeLabel: TextView
    lateinit var inputEmail: TextView
    lateinit var inputPassword: TextView
    lateinit var btnLogin: Button
    lateinit var btnRegister: TextView

    // TODO : implementar boton de recuperacion de contraseña
    lateinit var registerText: TextView

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerText = v.findViewById(R.id.registerText)
        welcomeLabel = v.findViewById(R.id.welcomeText)
        inputEmail = v.findViewById(R.id.inputEmail)
        inputPassword = v.findViewById(R.id.inputPassword)
        btnLogin = v.findViewById(R.id.btnLogin)
        btnRegister = v.findViewById(R.id.registerText)

        registerText.text = "¿No tenes cuenta?"
        welcomeLabel.setTextColor(Color.CYAN)
        welcomeLabel.text = "Bienvenido a MovieApp"
        btnRegister.text = "Registrate"
        btnRegister.setTextColor(Color.GREEN)
        btnRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }






        btnLogin.setOnClickListener {
            val userEmail: String = inputEmail.text.toString()
            val userPassword: String = inputPassword.text.toString()
            if (userEmail.isNotEmpty() and userPassword.isNotEmpty()) {
                val userIndex = baseUsuarios.indexOfFirst {
                    it.email == userEmail
                }
                if (userIndex != -1) {
                    if (userPassword.equals(baseUsuarios[userIndex].password)) {
                        val action =
                            loginFragmentDirections.actionLoginFragmentToSuccessLoginFragment(
                                baseUsuarios[userIndex].name
                            )
                        findNavController().navigate(action)
                    } else
                        Snackbar.make(v, "Contraseña incorrectos", Snackbar.LENGTH_SHORT)
                            .show()
                } else Snackbar.make(v, "Usuario inexistente", Snackbar.LENGTH_SHORT)
                    .show()
            } else
                Snackbar.make(v, "Los campos no pueden estar vacios", Snackbar.LENGTH_SHORT).show()
        }

    }
}



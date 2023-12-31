package com.utn.segundoparcial.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.utn.segundoparcial.data.models.AuthResult
import com.utn.segundoparcial.R
import com.utn.segundoparcial.ui.viewmodels.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var v: View
    private lateinit var inputMail : TextInputEditText
    private lateinit var inputPassword : TextInputEditText
    private lateinit var btnLogin : Button
    private lateinit var btnRegister : TextView




    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel : LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v  = inflater.inflate(R.layout.fragment_login, container, false)
        btnLogin = v.findViewById(R.id.loginButton)
        inputMail = v.findViewById(R.id.inputEmailEditText)
        inputPassword = v.findViewById(R.id.inputPasswordEditText)
        btnRegister = v.findViewById(R.id.registerButton)


        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnLogin.setOnClickListener(){
            if(inputMail.text.toString().isEmpty() || inputPassword.text.toString().isEmpty()){
                Snackbar.make(v, "Por favor complete todos los campos", Snackbar.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            else
                viewModel.signIn(inputMail.text.toString(), inputPassword.text.toString())
        }

        viewModel.signInResult.observe(viewLifecycleOwner) { authResult ->
            when (authResult) {
                is AuthResult.Success -> {
                    Snackbar.make(v, "Inicio de sesion exitoso", Snackbar.LENGTH_SHORT)
                        .show()
                    val action = LoginFragmentDirections.actionLoginFragmentToNavigationActivity()
                    findNavController().navigate(action)
                }
                is AuthResult.Error -> {
                    val errorMessage = authResult.message
                    when {
                        errorMessage.contains("Usuario no registrado") -> {
                            Snackbar.make(v, errorMessage, Snackbar.LENGTH_SHORT).show()
                        }
                        errorMessage.contains("Credenciales no válidas") -> {
                            Snackbar.make(v, errorMessage, Snackbar.LENGTH_SHORT).show()
                        }
                        errorMessage.contains("Verifica tu correo electrónico") -> {
                            Snackbar.make(v, errorMessage, Snackbar.LENGTH_SHORT).show()
                        }
                        else -> {
                            Snackbar.make(v, errorMessage, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
        btnRegister.setOnClickListener(){
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }

}
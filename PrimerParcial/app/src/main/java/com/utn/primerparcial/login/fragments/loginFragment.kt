package com.utn.primerparcial.login.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.utn.primerparcial.R
import com.utn.primerparcial.login.models.User

class loginFragment : Fragment() {


    lateinit var v: View
    lateinit var welcomeLabel: TextView
    lateinit var inputMail: TextInputEditText
    lateinit var inputPassword: TextInputEditText
    lateinit var btnLogin: Button
    lateinit var btnRegister: TextView
    var baseUsuarios : MutableList<User> = ArrayList<User>(
        listOf(
            User("Juan", "1234", "juan@gmail.com", "https://i.pinimg.com/originals/0f/6a/9a/0f6a9a0a1b0a0e1a0e1a0e1a0e1a0e1a.jpg", 20),
    ))
    // TODO : implementar boton de recuperacion de contraseña
    lateinit var registerText: TextView

    companion object {
        fun newInstance() = loginFragment()
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
        inputMail = v.findViewById(R.id.inputMailEditText)
        inputPassword = v.findViewById(R.id.inputPasswordEditText)
        btnLogin = v.findViewById(R.id.btnLogin)
        btnRegister = v.findViewById(R.id.registerText)





        welcomeLabel.text = "Bienvenido a MovieApp"
        btnRegister.setOnClickListener {
            val action = loginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }






        btnLogin.setOnClickListener {
            val userEmail: String = inputMail.text.toString()
            Log.d("Mail = ", userEmail)
            val userPassword: String = inputPassword.text.toString()
            if (userEmail.isNotEmpty() and userPassword.isNotEmpty()) {
                val userIndex = baseUsuarios.indexOfFirst {
                    it.email == userEmail }
                if (userIndex != -1) {
                    if (userPassword == baseUsuarios[userIndex].password) {
                        var userLogged = baseUsuarios[userIndex]
                        val action = loginFragmentDirections.actionLoginFragmentToNavActivity()
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



package com.utn.primerparcial.login.fragments

import android.content.Context
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
import com.utn.primerparcial.movies.database.AppDatabase

class loginFragment : Fragment() {


    lateinit var v: View
    lateinit var welcomeLabel: TextView
    lateinit var inputMail: TextInputEditText
    lateinit var inputPassword: TextInputEditText
    lateinit var btnLogin: Button
    lateinit var btnRegister: TextView

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
        btnRegister = v.findViewById(R.id.registerButton)

        AppDatabase.getInstance(v.context)!!.userDao()!!.fetchAllUsers()
        val baseUsuarios = AppDatabase.getInstance(v.context)!!.userDao()!!.getAllUsers()




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
                val userIndex = baseUsuarios!!.indexOfFirst {
                    it!!.email == userEmail }
                if (userIndex != -1) {
                    if (userPassword == baseUsuarios!![userIndex]!!.password) {
                        val userLogged = baseUsuarios[userIndex]
                        val sharedPref = requireActivity().getSharedPreferences("my_loggedUser", Context.MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putInt("loggedUserId", userLogged!!.id)
                        Log.d("loggedUserId", userLogged!!.id.toString())
                        editor.putString("loggedUserName", userLogged!!.name)
                        editor.putString("loggedUserMail", userLogged!!.email)
                        editor.putInt("loggedUserAge", userLogged!!.age)
                        editor.apply()
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



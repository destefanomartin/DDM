package com.utn.basiclogin.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.utn.basiclogin.R
import com.utn.basiclogin.entities.User


class loginFragment : Fragment() {

    lateinit var v : View
    var FirstUser : User = User("Martin", "Destefano", "example@gmail.com", "Android")
    lateinit var inputEmailAddress: EditText
    lateinit var inputPassword : EditText
    lateinit var sendRequestButton : Button

    var baseUsuarios : MutableList<User> = ArrayList<User>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)


        inputEmailAddress = v.findViewById(R.id.inputEmailAddress)
        inputPassword = v.findViewById(R.id.inputPassword)
        sendRequestButton = v.findViewById(R.id.sendRequestbutton)
        return v
    }

    override fun onStart() {
        super.onStart()

        baseUsuarios.add(User("Jose", "Perez", "jperez@gmail.com", "12345"))
        baseUsuarios.add(User("Jorge", "Fernandez", "jfernandez@gmail.com", "braida"))
        baseUsuarios.add(User("Pedro", "Trillo", "trillopedro@gmail.com", "casla"))
        baseUsuarios.add(User("Elba", "Jonazo", "elbajonazo@gmail.com", "rdi2023"))
        baseUsuarios.add(User("Elsa", "Capuntas", "elsacapuntas@gmail.com", "batalla12"))
        baseUsuarios.add(User("Renzo", "Gomez", "rgomez@gmail.com", "11!!!3324"))
        baseUsuarios.add(User("Federico", "Coria", "fcoria@gmail.com", "nosequeponer!"))


        sendRequestButton.setOnClickListener {
            val userEmail: String = inputEmailAddress.text.toString()
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
                Snackbar.make(v,"Los campos no pueden estar vacios",Snackbar.LENGTH_SHORT).show()
        }
    }


}
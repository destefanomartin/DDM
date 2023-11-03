package com.utn.primerparcial.login.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.utn.primerparcial.R
import com.utn.primerparcial.login.models.User
import com.utn.primerparcial.movies.database.AppDatabase

class registerFragment : Fragment() {

    companion object {
        fun newInstance() = registerFragment()
    }

    private lateinit var v : View
    private lateinit var registerPassword : TextInputEditText
    private lateinit var registerMail : TextInputEditText
    private lateinit var registerName : TextInputEditText
    private lateinit var registerAge : TextInputEditText
    private lateinit var registerButton : Button
    private lateinit var favMovie : TextInputEditText
    private lateinit var viewModel : RegisterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_register, container, false)

        registerPassword = v.findViewById(R.id.registerPasswordEditText)
        registerMail = v.findViewById(R.id.registerMailEditText)
        registerName = v.findViewById(R.id.registerNameEditText)
        registerAge = v.findViewById(R.id.registerAgeEditText)
        registerButton = v.findViewById(R.id.sendData)
        favMovie = v.findViewById(R.id.registerFavMovieEditText)

        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerButton.setOnClickListener {
            if(registerPassword.text.toString().isNotEmpty() && registerMail.text.toString().isNotEmpty() && registerName.text.toString().isNotEmpty() && registerAge.text.toString().isNotEmpty()){
                viewModel.register(registerMail.toString(), registerPassword.toString())
            }else{
                Snackbar.make(v, "Por favor, complete todos los campos", Snackbar.LENGTH_LONG).show()
            }
            if(updateUI(viewModel.auth.currentUser)){
                Snackbar.make(v, "Usuario registrado con exito", Snackbar.LENGTH_LONG).show()
            requireActivity().onBackPressed()

        }

    }



    fun updateUI(user: User){
        registerName.setText(user.name)
        registerMail.setText(user.email)
        registerPassword.setText(user.password)
        registerAge.setText(user.age.toString())
        favMovie.setText(user.favMovie)
    }
}
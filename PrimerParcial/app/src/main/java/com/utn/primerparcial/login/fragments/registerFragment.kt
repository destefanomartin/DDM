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
import com.google.android.material.textfield.TextInputEditText
import com.utn.primerparcial.R

class registerFragment : Fragment() {

    companion object {
        fun newInstance() = registerFragment()
    }

    private lateinit var viewModel: RegisterViewModel
    private lateinit var v : View
    private lateinit var registerPassword : TextInputEditText
    private lateinit var registerMail : TextInputEditText
    private lateinit var registerName : TextInputEditText
    private lateinit var registerAge : TextInputEditText
    private lateinit var registerButton : Button
    private lateinit var gifImage : ImageView

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
        gifImage = v.findViewById(R.id.funnyImage)


        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
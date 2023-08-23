package com.utn.basiclogin.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.utn.basiclogin.R

class successLoginFragment : Fragment() {
    lateinit var v : View
    lateinit var welcomeLabel : TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.fragment_success_login, container, false)

        welcomeLabel = v.findViewById(R.id.welcomeTextView)
        return v
    }

    override fun onStart() {
        super.onStart()

        val user = successLoginFragmentArgs.fromBundle(requireArguments()).userName
        var message : String = "Bienvenido/a" + " " + user.toString()
        welcomeLabel.text = message

    }


}


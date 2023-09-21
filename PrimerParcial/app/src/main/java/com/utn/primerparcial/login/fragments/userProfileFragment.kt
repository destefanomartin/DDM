package com.utn.primerparcial.login.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.utn.primerparcial.R

class userProfileFragment : Fragment() {


    private lateinit var v : View
    private lateinit var name : TextView
    private lateinit var mail : TextView
    private lateinit var age : TextView
    private lateinit var favMovie : TextView
    private lateinit var userId : TextView
    private lateinit var settingsButton : FloatingActionButton
    private lateinit var logoutButton : ExtendedFloatingActionButton
    companion object {
        fun newInstance() = userProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.fragment_user_profile, container, false)

        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = v.findViewById(R.id.userNameTextView)
        mail = v.findViewById(R.id.emailTextView)
        age = v.findViewById(R.id.ageTextView)
        favMovie = v.findViewById(R.id.favMovieTextView)
        userId = v.findViewById(R.id.userIdTextView)
        settingsButton = v.findViewById(R.id.settingsButton)
        logoutButton = v.findViewById(R.id.logoutButton)


        name.text = requireActivity().getSharedPreferences("my_loggedUser", Context.MODE_PRIVATE)
            .getString("loggedUserName", "default")!!
        mail.text = requireActivity().getSharedPreferences("my_loggedUser", Context.MODE_PRIVATE)
            ?.getString("loggedUserMail", "default")!!
        age.text = requireActivity().getSharedPreferences("my_loggedUser", Context.MODE_PRIVATE)
            ?.getInt("loggedUserAge", 0)!!.toString()


        settingsButton.setOnClickListener {
            val action = userProfileFragmentDirections.actionUserProfileFragmentToSettingsActivity()
            findNavController().navigate(action)
        }

        logoutButton.setOnClickListener{
            logout()
        }
    }

    private fun logout () {
        val sharedPref = requireActivity().getSharedPreferences("my_loggedUser", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
        val action = userProfileFragmentDirections.actionUserProfileFragmentToMainNavgraph()
        findNavController().navigate(action)
    }

}
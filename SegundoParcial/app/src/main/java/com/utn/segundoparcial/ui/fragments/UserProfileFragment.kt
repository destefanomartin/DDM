package com.utn.segundoparcial.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.utn.segundoparcial.R
import com.utn.segundoparcial.ui.viewmodels.UserProfileViewModel

class UserProfileFragment : Fragment() {

    companion object {
        fun newInstance() = UserProfileFragment()
    }


    private val userProfileViewModel: UserProfileViewModel by viewModels()
    private lateinit var v : View
    private lateinit var name : TextView
    private lateinit var email : TextView
    private lateinit var country : TextView
    private lateinit var card : CardView
    private lateinit var signOutButton: Button
    private lateinit var imageView: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_user_profile, container, false)
        name = v.findViewById(R.id.userProfileName)
        email = v.findViewById(R.id.Email)
        country = v.findViewById(R.id.country)
        card = v.findViewById(R.id.card)
        signOutButton = v.findViewById(R.id.logOut)
        imageView = v.findViewById(R.id.userProfileImage)
        return v

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userProfileViewModel.getUserData()
        userProfileViewModel.user.observe(viewLifecycleOwner) { user ->
                if (user != null) {
                    name.text = user.name
                    email.text = user.email
                    country.text = user.country
                } else {
                    Log.d("TAG", "No such document")
                }
            }

        signOutButton.setOnClickListener(){
            userProfileViewModel.signOut()
            val action = UserProfileFragmentDirections.actionUserProfileFragment2ToNavGraph()
            findNavController().navigate(action)
        }

        card.setOnClickListener(){
            val action = UserProfileFragmentDirections.actionUserProfileFragment2ToLocationFragment()
            findNavController().navigate(action)
        }
    }
}



package com.utn.segundoparcial.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.utn.segundoparcial.R
import com.utn.segundoparcial.ui.viewmodels.UserProfileViewModel

class UserProfileFragment : Fragment() {

    companion object {
        fun newInstance() = UserProfileFragment()
    }

    private lateinit var viewModel: UserProfileViewModel
    private lateinit var v : View
    private lateinit var name : TextView
    private lateinit var email : TextView
    private lateinit var phone : TextView
    private lateinit var card : CardView
    private lateinit var button: Button
    private lateinit var imageView: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_user_profile, container, false)
        name = v.findViewById(R.id.userProfileName)
        email = v.findViewById(R.id.Email)
        phone = v.findViewById(R.id.Phone)
        card = v.findViewById(R.id.card)
        button = v.findViewById(R.id.logOut)
        imageView = v.findViewById(R.id.userProfileImage)
        return v

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
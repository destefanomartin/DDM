package com.utn.segundoparcial.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.utn.segundoparcial.AuthResult
import com.utn.segundoparcial.R
import com.utn.segundoparcial.ui.viewmodels.RegisterViewModel

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var v: View
    private lateinit var buttonRegister : Button
    private lateinit var inputMail  : TextInputEditText
    private lateinit var inputPassword : TextInputEditText
    private lateinit var inputRepPassword : TextInputEditText
    private lateinit var inputName : TextInputEditText
    private lateinit var check : MaterialSwitch
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.fragment_register, container, false)
        buttonRegister = v.findViewById(R.id.registerButton)
        inputMail = v.findViewById(R.id.inputEmailEditText)
        inputPassword = v.findViewById(R.id.inputPasswordEditText)
        inputRepPassword = v.findViewById(R.id.inputRepPasswordEditText)
        inputName = v.findViewById(R.id.inputUserNameEditText)
        check = v.findViewById(R.id.ageApproval)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        viewModel.signUpResult.observe(viewLifecycleOwner) { authResult ->
            when (authResult) {
                is AuthResult.Success -> {
                    Snackbar.make(v, "Aber jeje", Snackbar.LENGTH_SHORT)
                        .show()
                }
                is AuthResult.Error -> {
                }
            }
        }

        buttonRegister.setOnClickListener {

            if(check.isChecked){
                val email = inputMail.text.toString()
                val password = inputPassword.text.toString()
                val repPassword = inputRepPassword.text.toString()
                val name = inputName.text.toString()

                viewModel.signUp(email, password, repPassword, name)
            }
            else {
                Snackbar.make(v, "Debe aceptar las politicas de privacidad y los terminos y condiciones", Snackbar.LENGTH_SHORT)
                    .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
                    .show()
            }

        }
    }


}





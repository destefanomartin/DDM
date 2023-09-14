package com.example.settingsactivity.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.settingsactivity.R

class MainFragment : Fragment() {

    lateinit var v : View
    lateinit var btnSettings : Button

    var valor : Int? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_main, container, false)

        btnSettings = v.findViewById(R.id.btn_settings)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        Log.d("Test",prefs.getBoolean("sync",false).toString())
        Log.d("Test",prefs.getString("reply","").toString())
        Log.d("Test",prefs.getString("signature","default signature").toString())

        btnSettings.setOnClickListener {

            val action = MainFragmentDirections.actionMainFragmentToSettingsActivity2()
            v.findNavController().navigate(action)

        }
    }

}

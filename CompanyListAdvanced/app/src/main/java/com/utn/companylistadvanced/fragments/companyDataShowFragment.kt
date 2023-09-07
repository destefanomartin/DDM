package com.utn.companylistadvanced.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.utn.companylistadvanced.R

class companyDataShowFragment : Fragment() {

    private lateinit var v: View
    lateinit var logoCompanyShow : ImageView
    lateinit var nameCompanyShow : TextView
    lateinit var zipCodeCompanyShow : TextView
    lateinit var locationCompanyShow : TextView
    lateinit var employeesCompanyShow : TextView
    companion object {
        fun newInstance() = companyDataShowFragment()
    }

    private lateinit var viewModel: CompanyDataShowViewModel
    private val args: companyDataShowFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_company_data_show, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logoCompanyShow = v.findViewById(R.id.imgLogoShow)
        nameCompanyShow = v.findViewById(R.id.nameCompanyShow)
        zipCodeCompanyShow = v.findViewById(R.id.zipCodeCompanyShow)
        locationCompanyShow = v.findViewById(R.id.locationCompanyShow)
        employeesCompanyShow = v.findViewById(R.id.employeesCompanyShow)


        Glide.with(logoCompanyShow)
            .load(args.company.logo)
            .centerCrop()
            .into(logoCompanyShow)

        nameCompanyShow.text = args.company.name
        zipCodeCompanyShow.text = args.company.zipCode.toString()
        locationCompanyShow.text = args.company.location
        employeesCompanyShow.text = args.company.employees.toString()

    }

}
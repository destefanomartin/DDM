package com.utn.companylistadvanced.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.utn.companylistadvanced.R
import com.utn.companylistadvanced.adapters.CompaniesAdapter
import com.utn.companylistadvanced.models.Company

class companiesListFragment : Fragment() {

    companion object {
        fun newInstance() = companiesListFragment()
    }

    private lateinit var v: View
    private lateinit var rvCompanies : RecyclerView
    private lateinit var viewModel: CompanysListViewModel
    private val db = Firebase.firestore

    val company = Company(0, "1", "1", "1", "1", 1)

    /*  Old code from the previous class
        private var companiesList : MutableList<Company> = mutableListOf(
            Company("Apple", "Alabama", 1425, "https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?202106030101", 1200),
            Company("Microsoft", "Alabama", 33, "https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?202106030101", 100),
            Company("Bethesda", "Puerto Santa Maria", 232, "https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?202106030101", 12200),
            Company("Google", "Villa Langostura", 44, "https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?202106030101", 1500),
            Company("2K", "Mexico", 4325, "https://www.apple.com/ac/structured_data/images/knowledge_graph_logo.png?202106030101", 900)
        ) */





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_companys_list, container, false)
        return v
    }

    override fun onStart() {
        super.onStart()
        rvCompanies = v.findViewById(R.id.rvCompanies)


        db.collection("comapanies").document("Apple").set(company)

        db.collection("comapanies")
            .whereGreaterThan("employees", 1200)
            .get().addOnSuccessListener { snapshot ->
            val companiesList = mutableListOf<Company>()
            snapshot.documents.forEach { doc ->
                val compania = doc.toObject<Company>()
                if (compania != null) {
                    companiesList.add(compania)
                }
            }
            Log.d("CompaniesListFragment", "Companies: $companiesList")
            setupRecyclerView(companiesList)
    }

    }
    private fun setupRecyclerView(companiesList : List<Company>) {
        // Creo el adapter de la lista de companies y le paso la lista de companies junto con el callback




        val companiesAdapter = CompaniesAdapter(companiesList) { company ->
            Log.d("CompaniesListFragment", "Company: ${company.name}")
            val action = companiesListFragmentDirections.actionCompanysListFragmentToCompanyDataShowFragment(company)
            findNavController().navigate(action)
        }

        val adapter2 = CompaniesAdapter(companiesList, ::onCompanyClicked)

        with(rvCompanies) {
            adapter = companiesAdapter
            layoutManager = LinearLayoutManager(context)

        }
        //rvCompanies.adapter = companiesAdapter
        //rvCompanies.layoutManager = LinearLayoutManager(context)
        // Se puede hacer individualmente con rvCompanies. y lo que quiero
        //rvCompanies.removeAllViewsInLayout()
    }

    private fun onCompanyClicked(company: Company) {
        Log.d("CompaniesListFragment1", "Company: ${company.name}")
    }
}
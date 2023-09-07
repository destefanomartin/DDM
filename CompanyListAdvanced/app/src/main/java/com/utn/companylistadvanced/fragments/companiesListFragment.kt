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
import com.utn.companylistadvanced.R
import com.utn.companylistadvanced.adapters.CompaniesAdapter
import com.utn.companylistadvanced.models.Company

class companiesListFragment : Fragment() {

    companion object {
        fun newInstance() = companiesListFragment()
    }

    private var companiesList : MutableList<Company> = mutableListOf(
        Company("Apple", "Alabama", 1425, "https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?202106030101", 1200),
        Company("Microsoft", "Alabama", 33, "https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?202106030101", 100),
        Company("Bethesda", "Puerto Santa Maria", 232, "https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?202106030101", 12200),
        Company("Google", "Villa Langostura", 44, "https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?202106030101", 1500),
        Company("2K", "Mexico", 4325, "https://www.apple.com/ac/structured_data/images/knowledge_graph_logo.png?202106030101", 900)
    )

    private lateinit var v: View

    private lateinit var rvCompanies : RecyclerView
    private lateinit var viewModel: CompanysListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_companys_list, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCompanies = v.findViewById(R.id.rvCompanies)
        setupRecyclerView()
    }
    private fun setupRecyclerView() {
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
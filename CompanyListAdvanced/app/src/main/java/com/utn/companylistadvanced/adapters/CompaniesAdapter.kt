package com.utn.companylistadvanced.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.utn.companylistadvanced.R
import com.utn.companylistadvanced.models.Company

class CompaniesAdapter(private val list : List<Company>,     private val onItemClick: (Company) -> Unit) : RecyclerView.Adapter<CompaniesAdapter.ViewHolder>() {
// Le paso el onItemClick al adapter, (puntero a funcion)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.company_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val company = list[position]
        holder.bind(company)
        holder.setOnClickListener { onItemClick(company) } // Se lo paso al viewholder
    }
    inner class ViewHolder(private val v : View) : RecyclerView.ViewHolder(v) {
        fun bind(company : Company)
        {
            val txtCompanyName : TextView = v.findViewById(R.id.txtCompanyName)
            txtCompanyName.text = company.name

            val imgLogoCompany : ImageView = v.findViewById(R.id.imgLogoCompany)
            Glide.with(imgLogoCompany)
                .load(company.logo)
                .centerCrop()
                .into(imgLogoCompany)


        }

        fun setOnClickListener(onClick: () -> Unit) {
            v.setOnClickListener {
                onClick()
            }
        }
    }
}
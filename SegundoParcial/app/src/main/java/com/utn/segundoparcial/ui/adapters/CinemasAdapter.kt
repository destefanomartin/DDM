package com.utn.segundoparcial.ui.adapters

import android.content.Intent
import android.net.Uri
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.utn.segundoparcial.R
import com.utn.segundoparcial.data.models.Cinema
import com.utn.segundoparcial.data.models.Movie

class CinemasAdapter (private val list: MutableList<Cinema>?) : RecyclerView.Adapter<CinemasAdapter.CinemasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false)
        return CinemasViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: CinemasViewHolder, position: Int) {
        val cinema = list?.get(position)
        holder.bind(cinema!!)
    }

    inner class CinemasViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(cinema : Cinema) {
            val name : TextView = v.findViewById(R.id.cinemaName)
            val address : TextView = v.findViewById(R.id.address)
            val distance : TextView = v.findViewById(R.id.distance)
            val web : TextView = v.findViewById(R.id.homepage)
            val schedule : TextView = v.findViewById(R.id.schedule)

            name.text = cinema.name
            address.text = cinema.address
            distance.text = cinema.distance.toString() + " m"
            schedule.text = cinema.schedules

            if (!cinema.homepage.isNullOrBlank()) {
                val spannableString = SpannableString("Sitio Web")
                val clickableSpan = object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        // Acción cuando se hace clic en el enlace
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(cinema.homepage))
                        if (intent.resolveActivity(v.context.packageManager) != null) {
                            v.context.startActivity(intent)
                        } else {
                            // Manejar el caso en el que no hay actividad para manejar la intención
                            Toast.makeText(v.context, "No hay aplicación para manejar el enlace", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                spannableString.setSpan(clickableSpan, 0, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

                // Configurar el TextView para mostrar el texto con el enlace
                web.text = spannableString
                web.movementMethod = LinkMovementMethod.getInstance()
                web.visibility = View.VISIBLE
            } else {
                // Si el enlace no está presente, ocultar el TextView
                web.visibility = View.GONE
            }
        }
    }
}


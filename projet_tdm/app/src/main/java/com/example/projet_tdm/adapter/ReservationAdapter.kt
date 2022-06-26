package com.example.projet_tdm.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.projet_tdm.R
import com.example.projet_tdm.entity.Parking
import com.example.projet_tdm.entity.Reservation
import com.example.projet_tdm.load
import com.example.projet_tdm.ui.ParkingDetailsActivity
import com.example.projet_tdm.ui.ReservationDetailsActivity
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class ReservationAdapter(val context: Context, var data: List<Reservation>) :
    RecyclerView.Adapter<ReservationAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.reservation_layout, parent, false)
        )
    }

    override fun getItemCount() = data.size

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(
        holder: MyViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.apply {
            // here apply the shit
            reservationId.text = data[position]._id

            dateEntree.text =
                SimpleDateFormat("dd-MM-yyyy à hh:mm").format(data[position].dateEntree)
            dateSortie.text =
                SimpleDateFormat("dd-MM-yyyy à hh:mm").format(data[position].dateSortie)

            numero.text = data[position].numeroPlace.toString()
            details.setOnClickListener {
                // do some shit and redirect the user to a reservation detail screen where the QR code
                val intent = Intent(itemView.context, ReservationDetailsActivity::class.java)
                intent.putExtra("reservation", data[position])
                startActivity(itemView.context, intent, null)
            }
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // get the attributes
        val reservationId = view.findViewById<TextView>(R.id.reservation_id)
        val dateEntree = view.findViewById<TextView>(R.id.date_entree)
        val dateSortie = view.findViewById<TextView>(R.id.date_sortie)
        val numero = view.findViewById<TextView>(R.id.numero)
        val details = view.findViewById<TextView>(R.id.see_details)
    }
}

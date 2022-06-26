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


class ParkingsAdapter(val context: Context, var data: List<Parking>) :
    RecyclerView.Adapter<ParkingsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.parking_layout, parent, false)
        )
    }

    override fun getItemCount() = data.size

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(
        holder: MyViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.apply {
//             here apply the shit
            nom.text = data[position].nom
            com.text = data[position].commune
            // get time
            val time = LocalDateTime.now()
            if (time.hour > data[position].horraireOuver && time.hour < data[position].horraireFerm)
                etat.text = "OUVERT"
            else
                etat.text = "FERMER"

            prix.text = data[position].tarifHeure.toString() + "DA/H"

            open.text = "${data[position].horraireOuver}:00"
            close.text = "${data[position].horraireFerm}:00"

            Glide.with(this.itemView).load(data[position].imglink)
                .apply(RequestOptions())
                .placeholder(R.drawable.parking)
                .into(img)
            itemView.setOnClickListener {
                val bundle = bundleOf("parking_id" to data[position]._id)
                val intent = Intent(itemView.context, ParkingDetailsActivity::class.java)
                intent.putExtra("parking_id", data[position]._id)
                startActivity(itemView.context, intent, bundle)
            }
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // get the attributes
        val img = view.findViewById<ImageView>(R.id.img)
        val nom = view.findViewById<TextView>(R.id.p_nom)
        val com = view.findViewById<TextView>(R.id.p_com)
        val prix = view.findViewById<TextView>(R.id.p_prix)
        val open = view.findViewById<TextView>(R.id.p_open)
        val close = view.findViewById<TextView>(R.id.p_close)
        val etat = view.findViewById<TextView>(R.id.p_stat)
    }
}

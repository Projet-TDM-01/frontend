package com.example.projet_tdm.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.projet_tdm.R
import com.example.projet_tdm.entity.Parking
import com.example.projet_tdm.load
import java.time.LocalDateTime
import java.util.*


class SearchAdapter(val context: Context, var data: List<Parking>) :
    RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.search_layout, parent, false)
        )
    }

    override fun getItemCount() = data.size

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("CheckResult", "ResourceAsColor")
    override fun onBindViewHolder(
        holder: MyViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.apply {
            // here apply the shit
            nom.text = data[position].nom
            commune.text = data[position].commune
            // get time
            val time = LocalDateTime.now()
            if(time.hour > data[position].horraireOuver && time.hour < data[position].horraireFerm)
                etat.text = "OUVERT"
            else
                etat.text = "FERMER"

            Glide.with(this.itemView).load(data[position].imglink)
                .apply(RequestOptions())
                .placeholder(R.drawable.parking)
                .into(img)
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // get the attributes
        val nom = view.findViewById(R.id.search_nom) as TextView
        val commune = view.findViewById(R.id.search_commune) as TextView
        val etat = view.findViewById(R.id.search_staus) as TextView

        val img = view.findViewById(R.id.search_img) as ImageView
    }
}

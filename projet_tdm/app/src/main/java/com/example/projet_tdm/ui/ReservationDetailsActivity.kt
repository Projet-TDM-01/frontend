package com.example.projet_tdm.ui

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projet_tdm.R
import com.example.projet_tdm.entity.Reservation
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class ReservationDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_details)

        val extras = intent.extras
        val reservation = extras!!.getSerializable("reservation") as Reservation

        findViewById<TextView>(R.id.details_reservation_id).text = reservation._id
        findViewById<TextView>(R.id.res_d_nom).text = reservation.parking.nom
        findViewById<TextView>(R.id.res_d_commune).text = reservation.parking.commune
        findViewById<TextView>(R.id.res_d_numero).text = reservation.numeroPlace.toString()
        findViewById<TextView>(R.id.res_d_date_entree).text = reservation.dateEntree.toString()
        findViewById<TextView>(R.id.res_d_date_sortie).text = reservation.dateSortie.toString()

        val ivQRcode = findViewById<ImageView>(R.id.codeQR)
        val writer = QRCodeWriter()

        try{
            val bitMatrix = writer.encode(reservation.numeroPlace.toString(), BarcodeFormat.QR_CODE,512,512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for(x in 0 until width){
                for(y in 0 until height){
                    bmp.setPixel(x,y,if(bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                }
            }
            ivQRcode.setImageBitmap(bmp)

        }
        catch (e: WriterException){
            e.printStackTrace()
        }
    }
}
package com.example.projet_tdm

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class reservationActivity : AppCompatActivity() {
    private lateinit var tvTime : TextView
    private lateinit var btnTimePickerStart : Button

    private lateinit var tvTimeEnd : TextView
    private lateinit var btnTimePickerEnd : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        tvTime = findViewById(R.id.tvTime)
        btnTimePickerStart = findViewById(R.id.btnTimePickerStart)
        btnTimePickerStart.setOnClickListener{
            val currentTime = Calendar.getInstance()
            val startHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentTime.get(Calendar.MINUTE)
            TimePickerDialog(this , TimePickerDialog.OnTimeSetListener{
                view , hourOfDay , minute -> tvTime.setText("$hourOfDay:$minute")
            } , startHour , startMinute ,false).show()

        }


        tvTimeEnd = findViewById(R.id.tvTimeEnd)
        btnTimePickerEnd = findViewById(R.id.btnTimePickerEnd)
        btnTimePickerEnd.setOnClickListener{
            val currentTimeEnd = Calendar.getInstance()
            val startHourEnd = currentTimeEnd.get(Calendar.HOUR_OF_DAY)
            val startMinuteEnd = currentTimeEnd.get(Calendar.MINUTE)
            TimePickerDialog(this , TimePickerDialog.OnTimeSetListener{
                    view , hourOfDay , minute -> tvTimeEnd.setText("$hourOfDay:$minute")
            } , startHourEnd , startMinuteEnd ,false).show()

        }
    }
}
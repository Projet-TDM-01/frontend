package com.example.projet_tdm

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class reservationActivity : AppCompatActivity() {
    private lateinit var tvTime : TextView
    private lateinit var btnTimePickerStart : Button

    private lateinit var tvTimeEnd : TextView
    private lateinit var btnTimePickerEnd : Button

    private  lateinit var btnDatePicker : Button
    private lateinit var  tvDate : TextView
    var formatDate = SimpleDateFormat("dd-MMMM-yyyy" , Locale.UK)

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


        btnDatePicker = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener{
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(this , android.R.style.Theme_Holo_Light_Dialog_MinWidth , DatePickerDialog.OnDateSetListener{ datePicker, i, i2, i3 ->
                val selectDate = Calendar.getInstance()
                selectDate.set(Calendar.YEAR,i)
                selectDate.set(Calendar.MONTH , i2)
                selectDate.set(Calendar.DAY_OF_MONTH , i3)
                val date = formatDate.format(selectDate.time)
                Toast.makeText(this , "Date : " +date , Toast.LENGTH_SHORT).show()
                tvDate.text = date

            } , getDate.get(Calendar.YEAR) , getDate.get(Calendar.MONTH) , getDate.get(Calendar.DAY_OF_MONTH))

            datePicker.show()

        }

    }
    /*private fun updateLable(myCalendar : Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = sdf =

    }*/
}
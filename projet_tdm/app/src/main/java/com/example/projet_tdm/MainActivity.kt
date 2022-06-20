package com.example.projet_tdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        go_to_onboarding.setOnClickListener {
            intent = Intent(this,OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}
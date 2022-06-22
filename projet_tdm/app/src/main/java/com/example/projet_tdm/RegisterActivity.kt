package com.example.projet_tdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projet_tdm.entity.User
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.*
import java.sql.Types.NULL

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        go_to_login.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        register.setOnClickListener {

          if (nom.text.trim().isNotEmpty() && prenom.text.trim().isNotEmpty() && email.text.trim()
                    .isNotEmpty() && phone.text.trim().isNotEmpty() && password.text.trim()
                    .isNotEmpty()
            ) {
                //     if (password.text == ConfirmedPassword.text) {
                val user = User(
                    userId = NULL,
                    nom.text.toString() ,
                    prenom.text.toString(),
                    email.text.toString(),
                    phone.text.toString(),
                    password.text.toString()
                    )
              Toast.makeText(this, user.toString(), Toast.LENGTH_LONG).show()

                addUser(user)
                //  }
                // else{
                //Toast.makeText(this,"Mot de passe non confirmer",Toast.LENGTH_LONG).show()
                // }
            } else {
                Toast.makeText(this, "Required", Toast.LENGTH_LONG).show()
            }


        }
    }

    private fun addUser(user: User) {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            runOnUiThread {
                println("erreur: "+throwable.message)
            }

        }

        CoroutineScope(Dispatchers.IO+exceptionHandler).launch {

            val response = RESTAPI.createEndpoint().addUser(user)
           //les operation d'affichage il faut les faires dans maines threads ou utiliser cette fonction
                if (response.isSuccessful) {
                    println(response.body())
                  //  Toast.makeText(this, ", Toast.LENGTH_LONG).show()
                } else {
                    println(response.body())
                }


        }


    }


}


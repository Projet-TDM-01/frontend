package com.example.projet_tdm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //  val p

        go_to_register.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            //Toast.makeText(this,"mot de passe ou nom d'utilisateur incorrecte", Toast.LENGTH_LONG).show()
            // getuser() ;
            //if ( email.text.trim().isNotEmpty()&& password.text.trim().isNotEmpty()
            // ) {
            //     if (password.text == ConfirmedPassword.text) {


            //     }
            //   else{  email.text.toString(),
            //                    phone.text.toString(),
            //                    password.text.toString()
            val info = QuoteGetUserId(
                email = "himamerakchi@gmail.com" ,
                password = "hibahiba"
            )
            val data = HashMap<String,String>()
            data.put("email","himamerakchi@gmail.com")
            data.put("password","hibahiba")

            getuser( data)
            //   Toast.makeText(this,"Mot de passe non confirmer",Toast.LENGTH_LONG).show()
        }
        //  } else {
        // Toast.makeText(this, "Required", Toast.LENGTH_LONG).show()
        //}
    }

    private fun getuser( data:Map<String,String>) {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            runOnUiThread {
                println("erreur :" + throwable.message)
            }

        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {


            val response = RESTAPI.createEndpoint().getUserId(data)
            //les operation d'affichage il faut les faires dans maines threads ou utiliser cette fonction
            if (response.isSuccessful) {
                println("Response success :"+response.body())
                //  Toast.makeText(this, ", Toast.LENGTH_LONG).show()
            } else {
                println("Response Failure :"+response.body())
            }

        }
    }
}


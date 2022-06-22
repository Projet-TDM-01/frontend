package com.example.projet_tdm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.projet_tdm.databinding.ActivityLoginBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {
    lateinit var  fbBtn : LoginButton;
    var TAG = "Parks"
    lateinit var  mainBinding : ActivityLoginBinding
    lateinit var callbackManager : CallbackManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        /*mainBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)*/
        callbackManager = CallbackManager.Factory.create()
        mainBinding.loginButton.setReadPermissions(listOf("email" , "public_profile" , "user_gender" , "user_birthday" , "user_friends"))
        mainBinding.loginButton.registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult?) {
                    val graphRequest = GraphRequest.newMeRequest(loginResult?.accessToken){`object` , response->
                        getFacebookData(`object`)
                    }
                    val parameters = Bundle()
                    parameters.putString("fields" , "id,email,birthday,friends,gender,name" )
                    graphRequest.parameters = parameters
                    graphRequest.executeAsync()

                }

                override fun onCancel() {
                    // App code
                }

                override fun onError(exception: FacebookException) {
                    // App code
                }
            })


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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
    private fun getFacebookData(obj: JSONObject?) {
       /* val profilePic = "https://graph.facebook.com/${obj?.getString("id")}/picture?width=200&height=200"
        Glide.with(this)
            .load(profilePic)
            .into(mainBinding.imgAvatar)*/
        val name = obj?.getString("name")
        val birthday = obj?.getString("birthday")
        val gender = obj?.getString("gender")
        val total_count = obj?.getJSONObject("friends")?.getJSONObject("summary")
            ?.getString("total_cuont")
        val email = obj?.getString("email")
        mainBinding.tvName.text = "NAME : ${name}"
        mainBinding.tvEmail.text = "EMAIL : ${email}"
        mainBinding.tvGender.text = "GENDER : ${gender}"
        mainBinding.tvDob.text = "DOB : ${birthday}"
        mainBinding.tvFriends.text = "FRIENDS COUNT : ${total_count}"




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


package com.example.projet_tdm

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.projet_tdm.ViewModels.UserViewModel
import com.example.projet_tdm.databinding.ActivityLoginBinding
import com.example.projet_tdm.retrofit.authentification
import io.reactivex.disposables.CompositeDisposable


/*
class LoginActivity : AppCompatActivity() {
    lateinit var  fbBtn : LoginButton;
    lateinit var loginbtn : Button
    var TAG = "Parks"
    lateinit var  mainBinding : ActivityLoginBinding
    lateinit var callbackManager : CallbackManager


    lateinit var authService : authentification
    internal var compositeDiposable = CompositeDisposable()

    override fun onStop() {
        compositeDiposable.clear()
        super.onStop()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = authObject.getInstance()
        authService = retrofit.create(authService::class.java)
        loginbtn = findViewById(R.id.login)

        loginbtn.setOnClickListener{
            loginUser(email_phone.text.toString() , password.text.toString() )
        }





      /*  mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)*/

       /* mainBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        callbackManager = CallbackManager.Factory.create()
        mainBinding.loginButton.setReadPermissions(listOf("email" , "public_profile" , "user_gender" , "user_birthday" , "user_friends"))
        mainBinding.loginButton.registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult?) {
                    val graphRequest = GraphRequest.newMeRequest(loginResult?.accessToken){ `object`, response->
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

*/
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
   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }*/
    private fun loginUser(email :String , password : String) {
        compositeDiposable.add(authService.loginUser(email , password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                result -> Toast.makeText(this , ""+result, Toast.LENGTH_SHORT).show()
            }

        )
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
      /*  mainBinding.tvName.text = "NAME : ${name}"
        mainBinding.tvEmail.text = "EMAIL : ${email}"
        mainBinding.tvGender.text = "GENDER : ${gender}"
        mainBinding.tvDob.text = "DOB : ${birthday}"
        mainBinding.tvFriends.text = "FRIENDS COUNT : ${total_count}"

*/


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

*/

class LoginActivity : AppCompatActivity() {
    //lateinit var fbBtn: LoginButton;

    var TAG = "Parks"
    lateinit var mainBinding: ActivityLoginBinding
  //  lateinit var callbackManager: CallbackManager
    lateinit var userViewModel: UserViewModel

    lateinit var authService: authentification
    internal var compositeDiposable = CompositeDisposable()

    lateinit var binding: ActivityLoginBinding

    override fun onStop() {
        compositeDiposable.clear()
        super.onStop()


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // val retrofit = authObject.getInstance()
        //authService = retrofit.create(authService::class.java)
       // binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

 binding = ActivityLoginBinding.inflate(layoutInflater)
 setContentView(binding.root)
 var loginbtn: Button?
 loginbtn = findViewById(R.id.login)

        binding.goToRegister.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


 val userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
 userViewModel.errorMessage.value = null
 setupListers()

 binding.login.setOnClickListener {
     if (isValid()) {
         val input = binding.emailPhone.text.toString()
         val passwordUser = binding.password.text.toString()
         var data: HashMap<String, String> = HashMap<String, String>()
         if (android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
             data["email"] = input;
             data["password"] = passwordUser;
             userViewModel.loginUserEmail(data)
         } else if (android.util.Patterns.PHONE.matcher(input).matches()) {
             data["phone"] = input;
             data["password"] = passwordUser;
             userViewModel.loginUserPhone(data)
         } else {
             Toast.makeText(
                 this ,
                 "Les données saisies sont invalides!",
                 Toast.LENGTH_SHORT
             ).show()
         }
     }
 }
/* userViewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
     if (!loading) {
         binding.progressBarLogin.visibility = View.GONE
     } else {
         binding.progressBarLogin.visibility = View.VISIBLE
     }
 })*/
/* userViewModel.errorMessage.observe(viewLifecycleOwner, Observer { message ->
     if (message != null) {
         Toast.makeText(
             requireActivity(),
             "Une erreur s'est produite",
             Toast.LENGTH_SHORT
         ).show()
         Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
     }
 })*/

 userViewModel.errorMessage.observe(this, Observer { message ->
     if (message != null) {
         Toast.makeText(this, "Une erreur s'est produite", Toast.LENGTH_SHORT)
             .show()
         Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
     }
 })
}

inner class textFieldValidation(private val view: View) : TextWatcher {
 override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
 }

 override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
     when (view.id) {
         R.id.email -> {
             validateInput()
         }
         R.id.password -> {
             validatePassword()
         }
     }
 }

 override fun afterTextChanged(p0: Editable?) {
 }
}

fun isValid(): Boolean = validateInput() && validatePassword()

fun validateInput(): Boolean {
 if (binding.emailPhone.text.toString().trim().isEmpty()) {
     binding.emailPhone.error = "Champs requis!"
     binding.emailPhone.requestFocus()
     return false
 } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.emailPhone.text.toString())
         .matches() && !android.util.Patterns.PHONE.matcher(binding.emailPhone.text.toString())
         .matches()
 ) {
     binding.emailPhone.error = "Email/Numero de telephone invalide!"
     binding.emailPhone.requestFocus()
     return false
 } else {
    // binding.emailPhone.isErrorEnabled = false
 }
 return true
}

fun validatePassword(): Boolean {
 if (binding.password.text.toString().isEmpty()) {
     binding.emailPhone.error = "Champs obligatoire."
     binding.password.requestFocus()
     return false
 } else if (binding.password.text.toString().length < 6) {
     binding.password.error =
         "Le mot de passe doit contenir au moins 6 caractères."
     binding.password.requestFocus()
     return false
 }
 return true
}

fun setupListers() {
 binding.emailPhone.addTextChangedListener(textFieldValidation(binding.emailPhone))
 binding.password.addTextChangedListener(textFieldValidation(binding.password))
}
}
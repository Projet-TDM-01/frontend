package com.example.projet_tdm


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
/*
class LoginActivity : AppCompatActivity() {
    lateinit var  fbBtn : LoginButton;
    lateinit var loginbtn : Button
    var TAG = "Parks"
    lateinit var  mainBinding : ActivityLoginBinding
    lateinit var callbackManager : CallbackManager
lateinit var userViewModel : UserViewModel

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

        ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        userViewModel.errorMessage.value = null
        setupListers()
        ActivityLoginBinding..setOnClickListener {
            activity?.findNavController(R.id.navHost)
                ?.navigate(R.id.action_loginFragment_to_registerFragment)
        }
        Binding.login.setOnClickListener {
            if (isValid()) {
                val input = Binding.email.text.toString()
                val passwordUser = Binding.mdp.text.toString()
                var data: HashMap<String, String> = HashMap<String, String>()
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
                    data["email"] = input;
                    data["mot_de_passe"] = passwordUser;
                    utilisateurViewModel.connexionUtilisateurEmail(data)
                } else if (android.util.Patterns.PHONE.matcher(input).matches()) {
                    data["numero_telephone"] = input;
                    data["mot_de_passe"] = passwordUser;
                    utilisateurViewModel.connexionUtilisateurNumeroTelephone(data)
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Les données saisies sont invalides!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        utilisateurViewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            if (!loading) {
                Binding.progressBarLogin.visibility = View.GONE
            } else {
                Binding.progressBarLogin.visibility = View.VISIBLE
            }
        })
        utilisateurViewModel.errorMessage.observe(viewLifecycleOwner, Observer { message ->
            if (message != null) {
                Toast.makeText(
                    requireActivity(),
                    "Une erreur s'est produite",
                    Toast.LENGTH_SHORT
                ).show()
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        })
        utilisateurViewModel.utilisateurs.observe(viewLifecycleOwner, Observer { utilisateurs ->
            if (utilisateurViewModel.loading.value == false) {
                if (utilisateurs != null && utilisateurs.isEmpty()) {
                    var text = "La connexion a échouée"
                    val duration = Toast.LENGTH_LONG
                    var toast = Toast.makeText(context, text, duration)
                    toast.show()
                    text = "Email ou mot de passe incorrect"
                    toast = Toast.makeText(context, text, duration)
                    toast.show()
                } else if (utilisateurs != null && utilisateurs.isNotEmpty()) {
                    val itemMenu: MenuItem = utilisateurViewModel.menu!!.findItem(R.id.btnLogout)
                    itemMenu.isVisible = true
                    var utilisateur = utilisateurViewModel.utilisateurs.value?.get(0)
                    val pr = requireActivity().getSharedPreferences("db_privee", Context.MODE_PRIVATE)?.edit()
                    pr?.putBoolean("connected", true)
                    pr?.putString("email", utilisateur?.email)
                    pr?.putString("id_utilisateur", utilisateur?.id_utilisateur.toString())
                    pr?.putString("numero_telephone", utilisateur?.numero_telephone)
                    pr?.putString("mot_de_passe", utilisateur?.mot_de_passe)
                    pr?.putString("nom", utilisateur?.nom)
                    pr?.putString("prenom", utilisateur?.prenom)
                    pr?.apply()
                    activity?.findNavController(R.id.navHost)
                        ?.navigate(R.id.action_loginFragment_to_mesReservationFragment2)
                }
            }
        })
        utilisateurViewModel.errorMessage.observe(requireActivity(), Observer { message ->
            if (message != null) {
                Toast.makeText(requireContext(), "Une erreur s'est produite", Toast.LENGTH_SHORT)
                    .show()
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
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
                R.id.mdp -> {
                    validatePassword()
                }
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    fun isValid(): Boolean = validateInput() && validatePassword()

    fun validateInput(): Boolean {
        if (Binding.email.text.toString().trim().isEmpty()) {
            Binding.emailTextInputLayout.error = "Champs requis!"
            Binding.email.requestFocus()
            return false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Binding.email.text.toString())
                .matches() && !android.util.Patterns.PHONE.matcher(Binding.email.text.toString())
                .matches()
        ) {
            Binding.emailTextInputLayout.error = "Email/Numero de telephone invalide!"
            Binding.email.requestFocus()
            return false
        } else {
            Binding.emailTextInputLayout.isErrorEnabled = false
        }
        return true
    }

    fun validatePassword(): Boolean {
        if (Binding.mdp.text.toString().isEmpty()) {
            Binding.passwordTextInputLayout.error = "Champs requis!"
            Binding.mdp.requestFocus()
            return false
        } else if (Binding.mdp.text.toString().length <= 6) {
            Binding.passwordTextInputLayout.error =
                "Mot de passe doit contenir au moins 6 caracteres!"
            Binding.mdp.requestFocus()
            return false
        } else {
            Binding.passwordTextInputLayout.isErrorEnabled = false
        }
        return true
    }

    fun setupListers() {
        Binding.email.addTextChangedListener(textFieldValidation(Binding.email))
        Binding.mdp.addTextChangedListener(textFieldValidation(Binding.mdp))
    }
}*/
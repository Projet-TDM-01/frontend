package com.example.projet_tdm

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.projet_tdm.ViewModels.UserViewModel
import com.example.projet_tdm.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.registerStatus.value = null
        userViewModel.errorMessage.value = null
        binding.register.setOnClickListener {
            if (isValid()) {
                var data: HashMap<String, String> = HashMap<String, String>()
                val email = binding.email.text.toString()
                val password = binding.password.text.toString()
                val phone = binding.phone.text.toString()
                val nom = binding.nom.text.toString()
                val prenom = binding.prenom.text.toString()


                data["email"] = email;
                data["password"] = password;
                data["phone"] = phone;
                data["nom"] = nom;
                data["prenom"] = prenom;
                userViewModel.regiserUser(data)

            }
        }

        userViewModel.registerStatus.observe(this, Observer { registerStatus ->
            if (registerStatus != null) {
                val builder = AlertDialog.Builder(this)
                if (registerStatus) {
                    builder.setTitle("Status d'inscription")
                    builder.setMessage("Inscription effectuée avec succés")
                    builder.setIcon(android.R.drawable.ic_dialog_info)
                    builder.setNeutralButton("Ok") { dialogInterface, which ->
                        Toast.makeText(
                            this,
                            "Redirection vers l'ecran de connexion",
                            Toast.LENGTH_LONG
                        ).show()
                        userViewModel.registerStatus.value = null

                    }
                } else {
                    builder.setTitle("Status d'inscription")
                    builder.setMessage("Un probleme est survenu lors de l'inscription. Veuillez verifier les informations saisies et réessayer!")
                    builder.setIcon(android.R.drawable.ic_dialog_alert)
                    builder.setNeutralButton("Ok") { dialogInterface, which ->
                        Toast.makeText(
                            this,
                            "Veuillez réessayer s'il vous plait!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        })
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
                    validateEmail()
                }
                R.id.password -> {
                    validatePassword()
                }

                R.id.phone -> {
                    validatePhone()
                }
                R.id.nom -> {
                    validateNom()
                }
                R.id.prenom -> {
                    validatePrenom()
                }
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    fun isValid(): Boolean =
        validateNom() && validatePrenom() && validatePhone() && validateEmail() && validatePassword()

    fun validateEmail(): Boolean {
        if (binding.email.text.toString().trim().isEmpty()) {
            binding.email.error = "Champs obligatoire."
            binding.email.requestFocus()
            return false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.email.text.toString())
                .matches()
        ) {
            binding.email.error = "Email invalide."
            binding.email.requestFocus()
            return false
        }
        return true
    }

    fun validatePhone(): Boolean {
        if (binding.phone.text.toString().isEmpty()) {
            binding.phone.error = "Champs requis!"
            binding.phone.requestFocus()
            return false
        } else if (!android.util.Patterns.PHONE.matcher(binding.phone.text.toString())
                .matches()
        ) {
            binding.phone.error = "Numéro de téléphone invalide."
            binding.phone.requestFocus()
            return false
        }
        return true
    }

    fun validatePassword(): Boolean {
        if (binding.password.text.toString().isEmpty()) {
            binding.password.error = "Champs obligatoire."
            binding.password.requestFocus()
            return false
        } else if (binding.password.text.toString().length <= 6) {
            binding.password.error =
                "Mot de passe doit contenir au moins 6 caracteres!"
            binding.password.requestFocus()
            return false
        }
        return true
    }

    fun validateNom(): Boolean {
        if (binding.nom.text.toString().isEmpty()) {
            binding.nom.error = "Champs obligatoire!"
            binding.nom.requestFocus()
            return false
        } else if (binding.nom.text.toString().length < 3) {
            binding.nom.error =
                "nom doit contenir au moins 3 caractères."
            binding.nom.requestFocus()
            return false
        }
        return true
    }

    fun validatePrenom(): Boolean {
        if (binding.prenom.text.toString().isEmpty()) {
            binding.prenom.error = "Champs obligatoire"
            binding.prenom.requestFocus()
            return false
        } else if (binding.prenom.text.toString().length < 3) {
            binding.prenom.error =
                "prenom doit contenir au moins 3 caracteres!"
            binding.prenom.requestFocus()
            return false
        }
        return true
    }

    fun setupListers() {
        binding.email.addTextChangedListener(textFieldValidation(binding.email))
        binding.password.addTextChangedListener(textFieldValidation(binding.password))
        binding.phone.addTextChangedListener(textFieldValidation(binding.password))
        binding.nom.addTextChangedListener(textFieldValidation(binding.nom))
        binding.prenom.addTextChangedListener(textFieldValidation(binding.prenom))
    }
}

        /* go_to_login.setOnClickListener {
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


         }*/


   /* private fun addUser(user: User) {
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

*/



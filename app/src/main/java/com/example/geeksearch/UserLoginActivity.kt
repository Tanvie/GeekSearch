package com.example.geeksearch

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        val ulBtRegistration = findViewById<Button>(R.id.ulBtRegistration)
        val ulBtLogin = findViewById<Button>(R.id.ulBtLogin)

        val ulMail = findViewById<EditText>(R.id.ulEdMail)
        val ulPassword = findViewById<EditText>(R.id.ulEdPassword)

        val mauth: FirebaseAuth = Firebase.auth

        ulBtRegistration.setOnClickListener {
            startActivity(Intent(this, UserRegisterActivity::class.java))
        }

        ulBtLogin.setOnClickListener {
            val userMail = ulMail.text.toString()
            val userPassword = ulPassword.text.toString()
            if (TextUtils.isEmpty(userMail)) {
                Toast.makeText(
                    applicationContext, "Please provide Email!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(userPassword)) {
                Toast.makeText(
                    applicationContext, "Please provide password!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val currUser = mauth.currentUser

                if (currUser != null) {
                    //reload();
                }
                mauth.signInWithEmailAndPassword(userMail, userPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = mauth.currentUser
                            Toast.makeText(
                                baseContext, "Login Successful",
                                Toast.LENGTH_LONG
                            ).show()
                            startActivity(Intent(this, HomeActivity::class.java))
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }

        }


    }
}
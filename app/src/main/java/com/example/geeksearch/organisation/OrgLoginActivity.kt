package com.example.geeksearch.organisation

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geeksearch.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class OrgLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_org_login)

//        val laBtSkip = findViewById<Button>(R.id.laBtSkip)
        val olBtRegister = findViewById<Button>(R.id.olBtRegister)
        val olBtLogin = findViewById<Button>(R.id.olBtLogin)
        val et_login_mail = findViewById<EditText>(R.id.olEdMail)
        val et_login_password = findViewById<EditText>(R.id.olEdPassword)

        val mauth: FirebaseAuth = Firebase.auth

//        laBtSkip.setOnClickListener {
//            startActivity(Intent(this, OrgHomeActivity::class.java))
//        }

        olBtRegister.setOnClickListener {
            startActivity(Intent(this, OrgRegisterActivity::class.java))
        }

        olBtLogin.setOnClickListener {
            val orgMail = et_login_mail.text.toString()
            val orgPassword = et_login_password.text.toString()

            if (TextUtils.isEmpty(orgMail)) {
                Toast.makeText(
                    applicationContext, "Please provide a Email!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(orgPassword)) {
                Toast.makeText(
                    applicationContext, "Please provide a Password!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val currUser = mauth.currentUser
                if (currUser != null) {
                    //reload();
                }
                mauth.signInWithEmailAndPassword(orgMail, orgPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = mauth.currentUser
                            Toast.makeText(
                                baseContext, "Login Successful",
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(this, OrgHomeActivity::class.java))
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }

        }
    }
}
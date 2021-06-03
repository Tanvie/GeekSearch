package com.example.geeksearch.organisation

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geeksearch.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class OrgRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_org_register)

        var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

        val orBtLogin = findViewById<TextView>(R.id.tv_organisation_registration_login)
        val orEdName = findViewById<EditText>(R.id.et_organisation_registration_name)
        val orEdMail = findViewById<EditText>(R.id.et_organisation_registration_email)
        val orEdPassword = findViewById<EditText>(R.id.et_organisation_registration_password)
        val orEdLocation = findViewById<EditText>(R.id.et_organisation_registration_location)
        val orBtRegister = findViewById<Button>(R.id.btn_organisation_registration_register)


        orBtLogin.setOnClickListener {
            startActivity(Intent(this, OrgLoginActivity::class.java))
        }

        orBtRegister.setOnClickListener {

            var orgName = orEdName.text.toString()
            var orgEmail = orEdMail.text.toString()
            var orgLocation = orEdLocation.text.toString()
            var orgPassword = orEdPassword.text.toString()

            if (TextUtils.isEmpty(orgEmail)) {
                Toast.makeText(
                    applicationContext, "Please provide a Email!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(orgPassword)) {
                Toast.makeText(
                    applicationContext, "Please provide a Password!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(orgName)) {
                Toast.makeText(
                    applicationContext, "Please provide Organisation Name!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(orgLocation)) {
                Toast.makeText(
                    applicationContext, "Please provide Location!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                mAuth.createUserWithEmailAndPassword(orgEmail, orgPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val db = FirebaseFirestore.getInstance()
                            val org: MutableMap<String, Any> = HashMap()
                            org["orgName"] = orgName
                            org["orgEmail"] = orgEmail
                            org["orgLocation"] = orgLocation
                            org["orgPassword"] = orgPassword

                            db.collection("Organisations").document(orgEmail)
                                .set(org)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "organisation added", Toast.LENGTH_SHORT)
                                        .show()
                                    startActivity(Intent(this, OrgHomeActivity::class.java))
                                    finish()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(
                                        this,
                                        "organisation failed to add",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }


                        } else {
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
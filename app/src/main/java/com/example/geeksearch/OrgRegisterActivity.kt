package com.example.geeksearch

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class OrgRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_org_register)

        val database = FirebaseDatabase.getInstance()
//        val myRef = database.getReference("organisations")
        var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

        val orBtLogin = findViewById<Button>(R.id.orBtLogin)
        val orEdName = findViewById<EditText>(R.id.orEtName)
        val orEdMail = findViewById<EditText>(R.id.orEdMail)
        val orEdPassword = findViewById<EditText>(R.id.orEdPassword)
        val orEdLocation = findViewById<EditText>(R.id.orEdLocation)
        val orBtRegister = findViewById<Button>(R.id.orBtRegister)


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

                            val org = OrganisationModel(orgName, orgEmail, orgLocation, orgPassword)

                            FirebaseDatabase.getInstance().getReference("organisations")
                                .child(FirebaseAuth.getInstance().currentUser.uid)
                                .setValue(org).addOnCompleteListener(this) {
                                    Toast.makeText(
                                        baseContext, "Registration Done",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    startActivity(Intent(this, OrgHomeActivity::class.java))
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
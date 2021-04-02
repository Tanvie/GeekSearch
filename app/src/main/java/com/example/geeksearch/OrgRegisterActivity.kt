package com.example.geeksearch

import android.content.Intent
import android.os.Bundle
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
        val myRef = database.getReference("organisations")
        var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

        val orBtLogin = findViewById<Button>(R.id.orBtLogin)
        val orEdName = findViewById<EditText>(R.id.orEdName)
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
            print("In BT REGISTER")
            mAuth.createUserWithEmailAndPassword(orgEmail, orgPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        var org = Organisation(orgName, orgEmail, orgLocation, orgPassword)
//                        Log.d("First Error")

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
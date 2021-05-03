package com.example.geeksearch.user

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geeksearch.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)


        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

        val urBtLogin = findViewById<Button>(R.id.urBtLogin)
        val urBtRegister = findViewById<Button>(R.id.urBtRegister)

        val urEdMail = findViewById<EditText>(R.id.urEdMail)
        val urEdPassword = findViewById<EditText>(R.id.urEdPassword)
        val urEdName = findViewById<EditText>(R.id.urEdName)
        val urEdPhoneNo = findViewById<EditText>(R.id.urEdPhoneNo)
        val urEdLocation = findViewById<EditText>(R.id.urEdLocation)
        val urEdCollege = findViewById<EditText>(R.id.urEdCollege)
        val urEdDegree = findViewById<EditText>(R.id.urEdDegree)

        urBtLogin.setOnClickListener {
            startActivity(Intent(this, UserLoginActivity::class.java))
        }

        urBtRegister.setOnClickListener {

            val userEmail = urEdMail.text.toString()
            val userPassword = urEdPassword.text.toString()
            val userName = urEdName.text.toString()
            val userPhone = urEdPhoneNo.text.toString()
            val userLocation = urEdLocation.text.toString()
            val userCollege = urEdCollege.text.toString()
            val userDegree = urEdDegree.text.toString()

            if (TextUtils.isEmpty(userEmail)) {
                Toast.makeText(
                    applicationContext, "Please provide a Email!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(userPassword)) {
                Toast.makeText(
                    applicationContext, "Please provide a Password!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(userName)) {
                Toast.makeText(
                    applicationContext, "Please provide Your Name!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(userPhone)) {
                Toast.makeText(
                    applicationContext, "Please provide a Contact Number!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(userLocation)) {
                Toast.makeText(
                    applicationContext, "Please provide your Location!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(userCollege)) {
                Toast.makeText(
                    applicationContext, "Please provide your College Name!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(userDegree)) {
                Toast.makeText(
                    applicationContext, "Please provide your Degree!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val db = FirebaseFirestore.getInstance()
                            val user: MutableMap<String, Any> = HashMap()
                            user["userName"] = userName
                            user["password"] = userPassword
                            user["userDegree"] = userDegree
                            user["userCollege"] = userCollege
                            user["userLocation"] = userLocation
                            user["userEmail"] = userEmail
                            user["phoneNo"] = userPhone

                            db.collection("Users")
                                .add(user)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "user added", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this, UserHomeActivity::class.java))
                                }
                                .addOnFailureListener {
                                    Toast.makeText(this, "user failed to add", Toast.LENGTH_SHORT)
                                        .show()
                                }

                        } else {
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
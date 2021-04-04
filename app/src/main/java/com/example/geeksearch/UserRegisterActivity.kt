package com.example.geeksearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

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


            mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        val user = User(
                            userEmail,
                            userName,
                            userPhone,
                            userLocation,
                            userCollege,
                            userDegree
                        )

                        FirebaseDatabase.getInstance().getReference("users")
                            .child(FirebaseAuth.getInstance().currentUser.uid)
                            .setValue(user).addOnCompleteListener(this) {
                                Toast.makeText(
                                    baseContext, "Registration Done",
                                    Toast.LENGTH_LONG
                                ).show()

                                startActivity(Intent(this, HomeActivity::class.java))
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
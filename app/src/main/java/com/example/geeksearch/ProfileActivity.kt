package com.example.geeksearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val mStore: FirebaseFirestore=FirebaseFirestore.getInstance()

        val back=findViewById<Button>(R.id.Back)
        val name=findViewById<EditText>(R.id.Name)
        val phone=findViewById<EditText>(R.id.Phone)
        val college=findViewById<EditText>(R.id.College)
        val degree=findViewById<EditText>(R.id.Degree)
        val location=findViewById<EditText>(R.id.Location)
        val save=findViewById<Button>(R.id.SaveChanges)

        val username:String = mAuth.currentUser.displayName


        back.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
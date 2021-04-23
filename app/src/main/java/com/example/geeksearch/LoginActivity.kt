package com.example.geeksearch

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val mAuth: FirebaseAuth = Firebase.auth

        val laBtLoginUser = findViewById<ImageButton>(R.id.laBtLoginUser)
        val laBtLoginOrg = findViewById<ImageButton>(R.id.laBtLoginOrg)



        laBtLoginUser.setOnClickListener {
            if(mAuth.currentUser!=null)
            {
                startActivity(Intent(this, HomeActivity::class.java))
            }
            else
            {
                startActivity(Intent(this, UserLoginActivity::class.java))
            }
        }
        laBtLoginOrg.setOnClickListener {
            startActivity(Intent(this, OrgLoginActivity::class.java))
        }
    }
}
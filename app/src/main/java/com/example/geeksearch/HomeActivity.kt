package com.example.geeksearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val haIvProfile = findViewById<ImageView>(R.id.haIvProfile)
        val haBtChat = findViewById<Button>(R.id.haBtChat)
        val haBtCreateTeam = findViewById<Button>(R.id.haBtCreateTeam)
        val haBtJoinTeam = findViewById<Button>(R.id.haBtJoinTeam)
        val haBtLogout= findViewById<Button>(R.id.haBtLogout)
        val haBtProfile = findViewById<Button>(R.id.haBtProfile)

        haIvProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        haBtChat.setOnClickListener {
            startActivity(Intent(this@HomeActivity, ChatActivity::class.java))
        }

        haBtCreateTeam.setOnClickListener {
            startActivity(Intent(this@HomeActivity, CreateTeamActivity::class.java))
        }

        haBtJoinTeam.setOnClickListener {
            startActivity(Intent(this@HomeActivity, JoinTeamActivity::class.java))
        }

        haBtLogout.setOnClickListener {
            var mAuth= FirebaseAuth.getInstance()
            mAuth.signOut()
            Toast.makeText(
                applicationContext, "Logged Out Successfully",
                Toast.LENGTH_LONG
            ).show()
            startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
        }

        haBtProfile.setOnClickListener {
            startActivity(Intent(this@HomeActivity, ProfileActivity::class.java))
        }
    }
}
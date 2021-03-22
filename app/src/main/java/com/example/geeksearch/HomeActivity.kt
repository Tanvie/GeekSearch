package com.example.geeksearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val haIvProfile = findViewById<ImageView>(R.id.haIvProfile)
        val haBtChat = findViewById<Button>(R.id.haBtChat)
        val haBtCreateTeam = findViewById<Button>(R.id.haBtCreateTeam)
        val haBtJoinTeam = findViewById<Button>(R.id.haBtJoinTeam)

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


    }
}
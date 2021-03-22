package com.example.geeksearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val laBtSkip = findViewById<Button>(R.id.laBtSkip)
        laBtSkip.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
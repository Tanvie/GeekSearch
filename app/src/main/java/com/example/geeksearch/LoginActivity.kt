package com.example.geeksearch

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val laBtLoginUser = findViewById<ImageButton>(R.id.laBtLoginUser)
        val laBtLoginOrg = findViewById<ImageButton>(R.id.laBtLoginOrg)



        laBtLoginUser.setOnClickListener {
            startActivity(Intent(this, UserLoginActivity::class.java))
        }
        laBtLoginOrg.setOnClickListener {
            startActivity(Intent(this, OrgLoginActivity::class.java))
        }
    }
}
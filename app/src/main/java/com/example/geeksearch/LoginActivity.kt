package com.example.geeksearch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geeksearch.databinding.ActivityLoginBinding
import com.example.geeksearch.organisation.OrgLoginActivity
import com.example.geeksearch.user.UserLoginActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val laBtLoginUser = findViewById<ImageButton>(R.id.laBtLoginUser)
//        val laBtLoginOrg = findViewById<ImageButton>(R.id.laBtLoginOrg)


        binding.laBtLoginUser.setOnClickListener {
            startActivity(Intent(this, UserLoginActivity::class.java))
        }
        binding.laBtLoginOrg.setOnClickListener {
            startActivity(Intent(this, OrgLoginActivity::class.java))
        }
    }
}
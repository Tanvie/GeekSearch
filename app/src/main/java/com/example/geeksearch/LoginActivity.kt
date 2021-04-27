package com.example.geeksearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geeksearch.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val laBtLoginUser = findViewById<ImageButton>(R.id.laBtLoginUser)
//        val laBtLoginOrg = findViewById<ImageButton>(R.id.laBtLoginOrg)


//        laBtLoginUser.setOnClickListener {
//            startActivity(Intent(this, UserLoginActivity::class.java))
//        }
//        laBtLoginOrg.setOnClickListener {
//            startActivity(Intent(this, OrgLoginActivity::class.java))
//        }
    }
}
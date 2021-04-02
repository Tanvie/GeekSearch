package com.example.geeksearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class OrgLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_org_login)

        val laBtSkip = findViewById<Button>(R.id.laBtSkip)
        val olBtRegister = findViewById<Button>(R.id.olBtRegister)
        val olBtLogin = findViewById<Button>(R.id.olBtLogin)
        val et_login_mail = findViewById<EditText>(R.id.et_login_mail)
        val et_login_password = findViewById<EditText>(R.id.et_login_password)

        laBtSkip.setOnClickListener {
            startActivity(Intent(this, OrgHomeActivity::class.java))
        }

        olBtRegister.setOnClickListener {
            startActivity(Intent(this, OrgRegisterActivity::class.java))
        }

        olBtLogin.setOnClickListener {
            var orgMail = et_login_mail.text.toString()
            var orgPassword = et_login_password.text.toString()
        }
    }
}
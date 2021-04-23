package com.example.geeksearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class OrgHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_org_home)

        val ohBtAddEvent = findViewById<Button>(R.id.ohBtAddEvent)
        val haBtLogout= findViewById<Button>(R.id.haBtOrgLogout)

        ohBtAddEvent.setOnClickListener {
            startActivity(Intent(this, AddEventActivity::class.java))
        }

        //TODO: Code not debugged.
        haBtLogout.setOnClickListener {
            var mAuth= FirebaseAuth.getInstance()
            mAuth.signOut()
            Toast.makeText(
                applicationContext, "Logged Out Successfully",
                Toast.LENGTH_LONG
            ).show()
            startActivity(Intent(this@OrgHomeActivity, LoginActivity::class.java))
        }
    }
}
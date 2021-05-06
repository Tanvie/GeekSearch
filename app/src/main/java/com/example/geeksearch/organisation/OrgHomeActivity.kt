package com.example.geeksearch.organisation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.geeksearch.OrgProfileActivity
import com.example.geeksearch.R

class OrgHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_org_home)

        val ohBtAddEvent = findViewById<Button>(R.id.ohBtAddEvent)
        val btnOrgProfile = findViewById<ImageButton>(R.id.img_btn_org_profile)

        ohBtAddEvent.setOnClickListener {
            startActivity(Intent(this, AddEventActivity::class.java))
        }
        btnOrgProfile.setOnClickListener {
            startActivity(Intent(this, OrgProfileActivity::class.java))
        }
    }
}
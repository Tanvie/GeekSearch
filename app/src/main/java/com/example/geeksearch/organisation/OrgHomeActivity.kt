package com.example.geeksearch.organisation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.geeksearch.R

class OrgHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_org_home)

        val ohBtAddEvent = findViewById<Button>(R.id.ohBtAddEvent)

        ohBtAddEvent.setOnClickListener {
            startActivity(Intent(this, AddEventActivity::class.java))
        }
    }
}
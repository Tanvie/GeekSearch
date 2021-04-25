package com.example.geeksearch.organisation

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geeksearch.EventModel
import com.example.geeksearch.R
import com.google.firebase.database.FirebaseDatabase


class AddEventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)


        val ethackName = findViewById<EditText>(R.id.ethackName)
        val ethackDescrip = findViewById<EditText>(R.id.ethackDescrip)
        val etEligibility = findViewById<EditText>(R.id.etEligibility)
        val etRegOpen = findViewById<EditText>(R.id.etRegOpen)
        val etRegClose = findViewById<EditText>(R.id.etRegClose)
        val ethackStart = findViewById<EditText>(R.id.ethckstr)
        val ethackEnd = findViewById<EditText>(R.id.ethckEnd)
        val etPrizes = findViewById<EditText>(R.id.etPrizes)
        val etEventRegLink = findViewById<EditText>(R.id.etEventRegLink)
        val btsubmit = findViewById<Button>(R.id.btSubmit)
        val rbtSoftware = findViewById<RadioButton>(R.id.rbsoftware)
        val rbthardware = findViewById<RadioButton>(R.id.rbhardware)
        val rbBoth = findViewById<RadioButton>(R.id.rbBoth)

        var domain = String()

        if (rbtSoftware.isChecked) {
            domain = "Software"
        } else if (rbthardware.isChecked) {
            domain = "Hardware"
        } else {
            domain = "Both Hardware and Software"
        }

        btsubmit.setOnClickListener {
            val hckname = ethackName.text.toString()
            val hckDes = ethackDescrip.text.toString()
            val eligibity = etEligibility.text.toString()
            val regOpenDate = etRegOpen.text.toString()
            val regCloseDate = etRegClose.text.toString()
            val regEventLink = etEventRegLink.text.toString()
            val hckStart = ethackStart.text.toString()
            val hckEnd = ethackEnd.text.toString()
            val prizes = etPrizes.text.toString()
            val domain = domain

            if (TextUtils.isEmpty(hckname)) {
                Toast.makeText(
                    applicationContext, "Hackathon Name missing!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(hckDes)) {
                Toast.makeText(
                    applicationContext, "Hackathon Description missing!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(eligibity)) {
                Toast.makeText(
                    applicationContext, "Provide Eligibilty!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(regOpenDate)) {
                Toast.makeText(
                    applicationContext, "Registration Open Date Missing!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(regCloseDate)) {
                Toast.makeText(
                    applicationContext, "Registration Close Date Missing!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(hckStart)) {
                Toast.makeText(
                    applicationContext, "Hackathon Start Date Missing!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(hckEnd)) {
                Toast.makeText(
                    applicationContext, "Hackathon End Date Missing!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(prizes)) {
                Toast.makeText(
                    applicationContext, "Mention Prizes!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (TextUtils.isEmpty(regEventLink)) {
                Toast.makeText(
                    applicationContext, "Hackathon Registration Link Missing!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val event = EventModel(
                    hckname,
                    hckDes,
                    eligibity,
                    regOpenDate,
                    regCloseDate,
                    hckStart,
                    hckEnd,
                    prizes,
                    domain,
                    regEventLink
                )
                FirebaseDatabase.getInstance().getReference("Events")
                    .push()
                    .setValue(event).addOnCompleteListener(this) {
                        Toast.makeText(
                            baseContext, "Event Added",
                            Toast.LENGTH_LONG
                        ).show()

                        startActivity(Intent(this, OrgHomeActivity::class.java))
                    }
            }


        }

    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked
            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rbsoftware ->
                    if (checked) {
                        Toast.makeText(
                            applicationContext, "Software",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                R.id.rbhardware ->
                    if (checked) {
                        Toast.makeText(
                            applicationContext, "Hardware",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                R.id.rbBoth ->
                    if (checked) {
                        Toast.makeText(
                            applicationContext, "Both",
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }
        }
    }


}



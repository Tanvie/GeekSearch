package com.example.geeksearch.user.announcement.create

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.geeksearch.R
import com.example.geeksearch.user.user.UserHomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class CreateAnnouncementActivity : AppCompatActivity() {


    var startDay = 0
    var startMonth = 0
    var startYear = 0
    var hckStartSavedDay = 0
    var hckStartSavedMonth = 0
    var hckStartSavedYear = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_announcement)


        val etAnnHackName = findViewById<EditText>(R.id.et_user_ann_hack_title)
        val rbAnnHackDomainSw = findViewById<RadioButton>(R.id.rb_user_ann_hack_software)
        val rbAnnHackDomainHw = findViewById<RadioButton>(R.id.rb_user_ann_hack_hardware)
        val rbAnnHackDomainBoth = findViewById<RadioButton>(R.id.rb_user_ann_hack_both)
        val etAnnHackReq = findViewById<EditText>(R.id.et_user_ann_requirements)
        val etAnnHackMsg = findViewById<EditText>(R.id.et_user_ann_message)
        val btnUserAnnStartDate = findViewById<ImageButton>(R.id.img_btn_user_ann_start_date)
        val btnUserAnnEndDate = findViewById<ImageButton>(R.id.img_btn_user_ann_end_date)
        val etAnnHackStartDate = findViewById<EditText>(R.id.et_user_ann_hack_start_date)
        val etAnnHackEndDate = findViewById<EditText>(R.id.et_user_ann_hack_end_date)
        val btnAnnHackSubmit = findViewById<Button>(R.id.btn_user_ann_create_ann)
        val etAnnHackUsername = findViewById<EditText>(R.id.et_user_ann_user_name)
        var domain: String

        if (rbAnnHackDomainSw.isChecked) {
            domain = "Software"
        } else if (rbAnnHackDomainHw.isChecked) {
            domain = "Hardware"
        } else {
            domain = "Hardware and Software"
        }
        btnUserAnnStartDate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            startDay = calendar.get(Calendar.DAY_OF_MONTH)
            startMonth = calendar.get(Calendar.MONTH)
            startYear = calendar.get(Calendar.YEAR)
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                    val dateFinal = "" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year
                    etAnnHackStartDate.setText(dateFinal)
                },
                startYear,
                startMonth,
                startDay
            )
            datePickerDialog.show()
        }
        btnUserAnnEndDate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            startDay = calendar.get(Calendar.DAY_OF_MONTH)
            startMonth = calendar.get(Calendar.MONTH)
            startYear = calendar.get(Calendar.YEAR)
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                    val dateFinal = "" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year
                    etAnnHackEndDate.setText(dateFinal)
                },
                startYear,
                startMonth,
                startDay
            )
            datePickerDialog.show()
        }

        btnAnnHackSubmit.setOnClickListener {
            val hackName = etAnnHackName.text.toString()
            val hackDomain = domain
            val requirements = etAnnHackReq.text.toString()
            val message = etAnnHackMsg.text.toString()
            val hackStart = etAnnHackStartDate.text.toString()
            val hackEnd = etAnnHackEndDate.text.toString()
            val userName = etAnnHackUsername.text.toString()

            if (TextUtils.isEmpty(hackDomain) || TextUtils.isEmpty(userName) || TextUtils.isEmpty(
                    message
                ) || TextUtils.isEmpty(
                    requirements
                ) || TextUtils.isEmpty(hackName) || TextUtils.isEmpty(hackStart) || TextUtils.isEmpty(
                    hackEnd
                )
            ) {
                Toast.makeText(this, "All the fields are compulsory!", Toast.LENGTH_SHORT).show()
            } else {
                saveDataToFireStore(
                    hackName, hackDomain, requirements, message, hackStart, hackEnd, userName
                )
            }
        }

    }

    private fun saveDataToFireStore(
        hackName: String,
        hackDomain: String,
        requirements: String,
        message: String,
        hackStart: String,
        hackEnd: String,
        userName: String
    ) {
        val db = FirebaseFirestore.getInstance()
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val userMail = mAuth.currentUser.email.toString()


        val announcement: MutableMap<String, Any> = HashMap()
        announcement[""]
        announcement["hackName"] = hackName
        announcement["hackDomain"] = hackDomain
        announcement["requirements"] = requirements
        announcement["message"] = message
        announcement["hackStart"] = hackStart
        announcement["hackEnd"] = hackEnd
        announcement["userName"] = userName
        announcement["userEmail"] = userMail

        db.collection("Announcements")
            .add(announcement)
            .addOnSuccessListener {
                Toast.makeText(this, "Announcement added", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, UserHomeActivity::class.java))
            }
            .addOnFailureListener {
                Toast.makeText(this, "Announcement ailed to add!", Toast.LENGTH_SHORT).show()
            }
    }


    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked
            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rb_user_ann_hack_software ->
                    if (checked) {
                        Toast.makeText(
                            applicationContext, "Software",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                R.id.rb_user_ann_hack_hardware ->
                    if (checked) {
                        Toast.makeText(
                            applicationContext, "Hardware",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                R.id.rb_user_ann_hack_both ->
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
package com.example.geeksearch.organisation

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.webkit.URLUtil
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.geeksearch.R
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap


class AddEventActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {


    var startDay = 0
    var startMonth = 0
    var startYear = 0
    var hckStartSavedDay = 0
    var hckStartSavedMonth = 0
    var hckStartSavedYear = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)


        val ethackName = findViewById<EditText>(R.id.ethackName)
        val ethackDescrip = findViewById<EditText>(R.id.ethackDescrip)
        val etEligibility = findViewById<EditText>(R.id.etEligibility)
        val etRegOpen = findViewById<EditText>(R.id.etRegOpen)
        val etRegClose = findViewById<EditText>(R.id.etRegClose)
        val etHackStart = findViewById<EditText>(R.id.ethckstr)
        val ethackEnd = findViewById<EditText>(R.id.ethckEnd)
        val etPrizes = findViewById<EditText>(R.id.etPrizes)
        val etEventRegLink = findViewById<EditText>(R.id.etEventRegLink)
        val btsubmit = findViewById<Button>(R.id.btSubmit)
        val rbtSoftware = findViewById<RadioButton>(R.id.rbsoftware)
        val rbthardware = findViewById<RadioButton>(R.id.rbhardware)
        val btnHckStart = findViewById<ImageButton>(R.id.btnHckStart)
        val btnRegStart = findViewById<ImageButton>(R.id.btnRegStart)
        val btnRegEnd = findViewById<ImageButton>(R.id.btnRegEnd)
        val btnHckEnd = findViewById<ImageButton>(R.id.btnHckEnd)

        var domain: String

        if (rbtSoftware.isChecked) {
            domain = "Software"
        } else if (rbthardware.isChecked) {
            domain = "Hardware"
        } else {
            domain = "Both Hardware and Software"
        }

        btnHckStart.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            startDay = calendar.get(Calendar.DAY_OF_MONTH)
            startMonth = calendar.get(Calendar.MONTH)
            startYear = calendar.get(Calendar.YEAR)
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                    val dateFinal = "" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year
                    etHackStart.setText(dateFinal)
                },
                startYear,
                startMonth,
                startDay
            )
            datePickerDialog.show()
        }

        btnHckEnd.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            startDay = calendar.get(Calendar.DAY_OF_MONTH)
            startMonth = calendar.get(Calendar.MONTH)
            startYear = calendar.get(Calendar.YEAR)
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                    val dateFinal = "" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year
                    ethackEnd.setText(dateFinal)
                },
                startYear,
                startMonth,
                startDay
            )
            datePickerDialog.show()
        }

        btnRegStart.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            startDay = calendar.get(Calendar.DAY_OF_MONTH)
            startMonth = calendar.get(Calendar.MONTH)
            startYear = calendar.get(Calendar.YEAR)
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                    val dateFinal = "" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year
                    etRegOpen.setText(dateFinal)
                },
                startYear,
                startMonth,
                startDay
            )
            datePickerDialog.show()
        }

        btnRegEnd.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            startDay = calendar.get(Calendar.DAY_OF_MONTH)
            startMonth = calendar.get(Calendar.MONTH)
            startYear = calendar.get(Calendar.YEAR)
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                    val dateFinal = "" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year
                    etRegClose.setText(dateFinal)
                },
                startYear,
                startMonth,
                startDay
            )
            datePickerDialog.show()
        }

        btsubmit.setOnClickListener {
            val hckName = ethackName.text.toString()
            val hckDes = ethackDescrip.text.toString()
            val eligibity = etEligibility.text.toString()
            val regOpenDate = etRegOpen.text.toString()
            val regCloseDate = etRegClose.text.toString()
            val regEventLink = etEventRegLink.text.toString()
            val hckStart = etHackStart.text.toString()
            val hckEnd = ethackEnd.text.toString()
            val prizes = etPrizes.text.toString()
            val domain = domain

            if (TextUtils.isEmpty(hckName) || TextUtils.isEmpty(regEventLink) || TextUtils.isEmpty(
                    prizes
                ) || TextUtils.isEmpty(hckEnd) || TextUtils.isEmpty(hckStart) || TextUtils.isEmpty(
                    regCloseDate
                ) || TextUtils.isEmpty(hckDes) || TextUtils.isEmpty(eligibity) || TextUtils.isEmpty(
                    regOpenDate
                )
            ) {
                Toast.makeText(
                    applicationContext, "All the fields are Compulsory!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (!(URLUtil.isValidUrl(regEventLink))) {
                Toast.makeText(this, "Event Link is Invalid!", Toast.LENGTH_SHORT).show()
            } else {
                saveFirestoreData(
                    hckName,
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
            }
        }
    }

    private fun saveFirestoreData(
        hckname: String,
        hckDes: String,
        eligibity: String,
        regOpenDate: String,
        regCloseDate: String,
        hckStart: String,
        hckEnd: String,
        prizes: String,
        domain: String,
        regEventLink: String
    ) {
        val db = FirebaseFirestore.getInstance()
        val event: MutableMap<String, Any> = HashMap()
        event["domain"] = domain
        event["eligibity"] = eligibity
        event["hckDes"] = hckDes
        event["hckEnd"] = hckEnd
        event["hckName"] = hckname
        event["hckStart"] = hckStart
        event["link"] = regEventLink
        event["prizes"] = prizes
        event["regCloseDate"] = regCloseDate
        event["regOpenDate"] = regOpenDate

        db.collection("Events")
            .add(event)
            .addOnSuccessListener {
                Toast.makeText(this, "Event added", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, OrgHomeActivity::class.java))
            }
            .addOnFailureListener {
                Toast.makeText(this, "record failed to add", Toast.LENGTH_SHORT).show()
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

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        hckStartSavedDay = p3
        hckStartSavedMonth = p2 + 1
        hckStartSavedYear = p1
    }
}



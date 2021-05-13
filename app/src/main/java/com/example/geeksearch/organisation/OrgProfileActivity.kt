package com.example.geeksearch.organisation

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geeksearch.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class OrgProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_org_profile)

        val tvOrgName = findViewById<TextView>(R.id.tv_org_profile_name)
        val tvOrgEmail = findViewById<TextView>(R.id.tv_org_profile_email)
        val tvOrgLoc = findViewById<TextView>(R.id.tv_org_location)


        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val orgMail = mAuth.currentUser.email.toString()
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("Organisations").document(orgMail)

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {

                    val org: MutableMap<String, Any> = document.data as MutableMap<String, Any>
                    tvOrgEmail.text = org["orgEmail"].toString()
                    tvOrgLoc.text = org["orgLocation"].toString()
                    tvOrgName.text = org["orgName"].toString()
                    Toast.makeText(this, "Profile data fetched", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error in Fetching data!", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "data Failed to fetch!", Toast.LENGTH_SHORT).show()
            }


    }

}
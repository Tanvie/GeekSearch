package com.example.geeksearch.user

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geeksearch.databinding.ActivityViewUserProfileBinding
import com.google.firebase.firestore.FirebaseFirestore

class ViewUserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inviterEmail = intent.getStringExtra("inviterEmail")

        val hackName = intent.getStringExtra("hack_name")
        val db = FirebaseFirestore.getInstance()
        val docRef = inviterEmail?.let { db.collection("Users").document(it) }
        docRef?.get()?.addOnSuccessListener { document ->
            if (document != null) {

                val user: MutableMap<String, Any> = document.data as MutableMap<String, Any>
                binding.tvInviterClg.text = user["userCollege"].toString()
                binding.tvInviterDegree.text = user["userDegree"].toString()
                binding.tvInviterName.text = user["userName"].toString()
                binding.tvInviterEmail.text = user["userEmail"].toString()
                binding.tvInviterPhoneNo.text = user["phoneNo"].toString()
                binding.tvInviterLocation.text = user["userLocation"].toString()
                binding.tvInviterCodechef.text = user["codeChefLink"].toString()
                binding.tvInviterLinkedIn.text = user["linkedInLink"].toString()
                binding.tvInviterHackerrank.text = user["hackerRankLink"].toString()
                binding.tvInviterSkills.text = user["userSkills"].toString()
                Toast.makeText(this, "Profile data fetched", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error in Fetching user!", Toast.LENGTH_SHORT).show()
            }
        }?.addOnFailureListener {
            Toast.makeText(this, "User Failed to fetch!", Toast.LENGTH_SHORT).show()
        }

        binding.btnInviterMailTo.setOnClickListener {
            val send: String =
                "mailto:$inviterEmail?subject=About Announcement made on GeekSearch about: $hackName"
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse(send)
            try {
                startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Error Displaying email", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
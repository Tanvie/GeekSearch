package com.example.geeksearch.user.user.profile

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geeksearch.R
import com.example.geeksearch.user.user.UserHomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions


class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val tvUserName = findViewById<TextView>(R.id.tv_user_name)
        val tvUserEmail = findViewById<TextView>(R.id.tv_user_email)
        val etUserPhone = findViewById<EditText>(R.id.et_user_phone_no)
        val etUserClg = findViewById<EditText>(R.id.et_user_clg)
        val etUserDegree = findViewById<EditText>(R.id.et_user_degree)
        val etUserLocation = findViewById<EditText>(R.id.et_user_location)
        val etUserCodeChef = findViewById<EditText>(R.id.et_user_codechef)
        val etUserLinkedIn = findViewById<EditText>(R.id.et_user_linkedIn)
        val etUserHackerRank = findViewById<EditText>(R.id.et_user_hackerrank)
        val etUserSkills = findViewById<EditText>(R.id.et_user_skills)
        val btnEditProfile = findViewById<Button>(R.id.bt_user_edit_profile)

        var clicked: Int = 0
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val userMail = mAuth.currentUser.email.toString()
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("Users").document(userMail)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {

                    val user: MutableMap<String, Any> = document.data as MutableMap<String, Any>
                    etUserClg.setText(user["userCollege"].toString())
                    etUserDegree.setText(user["userDegree"].toString())
                    tvUserName.text = user["userName"].toString()
                    tvUserEmail.text = user["userEmail"].toString()
                    etUserPhone.setText(user["phoneNo"].toString())
                    etUserLocation.setText(user["userLocation"].toString())
                    etUserCodeChef.setText(user["codeChefLink"].toString())
                    etUserLinkedIn.setText(user["linkedInLink"].toString())
                    etUserHackerRank.setText(user["hackerRankLink"].toString())
                    etUserSkills.setText(user["userSkills"].toString())
                    Toast.makeText(this, "Profile data fetched", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error in Fetching user!", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "User Failed to fetch!", Toast.LENGTH_SHORT).show()
            }
        etUserSkills.setOnClickListener {
            Toast.makeText(this, "Enter comma separated values", Toast.LENGTH_LONG).show()
        }
        btnEditProfile.setOnClickListener {
            clicked += 1

            if (clicked == 1) {
                etUserPhone.isFocusable = true
                etUserPhone.isFocusableInTouchMode = true
                etUserPhone.inputType = InputType.TYPE_CLASS_PHONE
                etUserClg.isFocusable = true
                etUserClg.isFocusableInTouchMode = true
                etUserClg.inputType = InputType.TYPE_CLASS_TEXT
                etUserDegree.isFocusable = true
                etUserDegree.isFocusableInTouchMode = true
                etUserDegree.inputType = InputType.TYPE_CLASS_TEXT
                etUserLocation.isFocusable = true
                etUserLocation.isFocusableInTouchMode = true
                etUserLocation.inputType = InputType.TYPE_CLASS_TEXT
                etUserLinkedIn.isFocusable = true
                etUserLinkedIn.isFocusableInTouchMode = true
                etUserLinkedIn.inputType = InputType.TYPE_TEXT_VARIATION_URI
                etUserCodeChef.isFocusable = true
                etUserCodeChef.isFocusableInTouchMode = true
                etUserCodeChef.inputType = InputType.TYPE_TEXT_VARIATION_URI
                etUserHackerRank.isFocusable = true
                etUserHackerRank.isFocusableInTouchMode = true
                etUserHackerRank.inputType = InputType.TYPE_TEXT_VARIATION_URI
                etUserSkills.isFocusable = true
                etUserSkills.isFocusableInTouchMode = true
                etUserSkills.inputType = InputType.TYPE_CLASS_TEXT

                btnEditProfile.text = "Save Profile"
            } else {
                val userPhone = etUserPhone.text.toString()
                val userCollege = etUserClg.text.toString()
                val userDegree = etUserDegree.text.toString()
                val userLoc = etUserLocation.text.toString()
                val userLinkedIn = etUserLinkedIn.text.toString()
                val userCodeChef = etUserCodeChef.text.toString()
                val userHackerRank = etUserHackerRank.text.toString()
                val userName = tvUserName.text.toString()
                val userEmail = tvUserEmail.text.toString()
                val userSkills = etUserSkills.text.toString()

                if (TextUtils.isEmpty(userSkills) || TextUtils.isEmpty(userPhone) || TextUtils.isEmpty(
                        userPhone
                    ) || TextUtils.isEmpty(
                        userCollege
                    ) || TextUtils.isEmpty(userDegree) || TextUtils.isEmpty(userCodeChef) || TextUtils.isEmpty(
                        userHackerRank
                    ) || TextUtils.isEmpty(userLinkedIn) || TextUtils.isEmpty(userLoc)
                ) {
                    Toast.makeText(this, "All Fields are Compulsory!!", Toast.LENGTH_SHORT).show()
                } else if (!(URLUtil.isValidUrl(userCodeChef)) || !(URLUtil.isValidUrl(userLinkedIn)) || !(URLUtil.isValidUrl(
                        userHackerRank
                    ))
                ) {
                    Toast.makeText(this, "Invalid URL!", Toast.LENGTH_SHORT).show()
                } else {

                    val user: MutableMap<String, Any> = HashMap()
                    user["userName"] = userName
                    user["userDegree"] = userDegree
                    user["userCollege"] = userCollege
                    user["userLocation"] = userLoc
                    user["userEmail"] = userEmail
                    user["phoneNo"] = userPhone
                    user["linkedInLink"] = userLinkedIn
                    user["hackerRankLink"] = userHackerRank
                    user["codeChefLink"] = userCodeChef
                    user["userSkills"] = userSkills

                    db.collection("Users").document(userEmail)
                        .set(user, SetOptions.merge())
                        .addOnSuccessListener {
                            Toast.makeText(this, "user data updated", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, UserHomeActivity::class.java))
                            finish()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "user failed to update", Toast.LENGTH_SHORT)
                                .show()
                        }

                }
            }
        }

    }
}
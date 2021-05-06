package com.example.geeksearch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.*
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {

//    lateinit var mUser: User
    lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var txtname=findViewById<EditText>(R.id.txtName)
        var phone=findViewById<EditText>(R.id.Phone)
        var college=findViewById<EditText>(R.id.College)
        var degree=findViewById<EditText>(R.id.Degree)
        var location=findViewById<EditText>(R.id.Location)
        var back=findViewById<Button>(R.id.Back)
        var save=findViewById<Button>(R.id.SaveChanges)
        //lateinit var txtname1: String

        mAuth= FirebaseAuth.getInstance()
        mDatabase=FirebaseDatabase.getInstance().reference
//        val userr=Firebase.auth.currentUser

//        lateinit var new: User
        lateinit var newUserName:String
        lateinit var newUserPhone:String
        lateinit var newUserLocation:String
        lateinit var newUserCollege:String
        lateinit var newUserDegree:String

        mDatabase.child("users").child(mAuth.currentUser.uid).get().addOnSuccessListener {
            Log.i("firebase", "Got value1 ${it.value}")
            var userdata: User? = it.getValue(User::class.java)
            txtname.setText(userdata?.userName)
            phone.setText(userdata?.userPhone)
            college.setText(userdata?.userCollege)
            degree.setText(userdata?.userDegree)
            location.setText(userdata?.userLocation)
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

//            .child("users").child(mAuth.currentUser.uid)

//        lateinit var userdata:User
//        val postListener=object:ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                var userdata: User?=snapshot.child("users").child(mAuth.currentUser.uid).getValue(User::class.java)         //might be a data leak here
//                Log.i("firebase", "Got value2 ${snapshot.child("users").child(mAuth.currentUser.uid).value}")
//                txtname.setText(userdata?.userName)
//                phone.setText(userdata?.userPhone)
//                college.setText(userdata?.userCollege)
//                degree.setText(userdata?.userDegree)
//                location.setText(userdata?.userLocation)
//                Log.i("Hello this is good", "name: ${userdata?.userName}")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("firebase","Error collecting data")
//            }
//        }
//        mDatabase.addValueEventListener(postListener)

        save.setOnClickListener {
            //val newUserEmail: String = "prathamesh.wavhal19@vit.edu"

            newUserName=txtname.text.toString()
            newUserPhone=phone.text.toString()
            newUserCollege=college.text.toString()
            newUserDegree=degree.text.toString()
            newUserLocation=location.text.toString()

//            val new = User(
//                newUserEmail,
//                newUserName,
//                newUserPhone,
//                newUserLocation,
//                newUserCollege,
//                newUserDegree
//            )

            mDatabase.child("users").child(mAuth.currentUser.uid).child("userName").setValue(newUserName)
            mDatabase.child("users").child(mAuth.currentUser.uid).child("userLocation").setValue(newUserLocation)
            mDatabase.child("users").child(mAuth.currentUser.uid).child("userPhone").setValue(newUserPhone)
            mDatabase.child("users").child(mAuth.currentUser.uid).child("userCollege").setValue(newUserCollege)
            mDatabase.child("users").child(mAuth.currentUser.uid).child("userDegree").setValue(newUserDegree)


//            val key=mDatabase.child("users").child(mAuth.currentUser.uid).push().key
//            if(key==null)
//            {
//                Log.w("fb","No key found!")
//            }
//            val childUpdates = hashMapOf<String, Any>(
//                "/users/$key" to new,
//                "/users-${mAuth.currentUser.uid}/$key" to new
//            )
//            mDatabase.updateChildren(childUpdates)

            Toast.makeText(
                applicationContext, "Changes Saved",
                Toast.LENGTH_LONG
            ).show()
            startActivity(Intent(this, HomeActivity::class.java))
        }

        back.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}


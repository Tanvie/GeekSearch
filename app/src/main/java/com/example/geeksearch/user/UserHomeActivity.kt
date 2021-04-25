package com.example.geeksearch.user

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geeksearch.ChatActivity
import com.example.geeksearch.EventModel
import com.example.geeksearch.R
import com.google.firebase.database.*


class UserHomeActivity : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var eventRecyclerview: RecyclerView
    private lateinit var eventModelArrayList: ArrayList<EventModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)

        val haIvProfile = findViewById<ImageView>(R.id.haIvProfile)
        val haBtChat = findViewById<Button>(R.id.haBtChat)
        val haBtCreateTeam = findViewById<Button>(R.id.haBtCreateTeam)
        val haBtJoinTeam = findViewById<Button>(R.id.haBtJoinTeam)
        eventRecyclerview = findViewById(R.id.recycler_view_event)

        eventRecyclerview.layoutManager = LinearLayoutManager(this)
        eventRecyclerview.setHasFixedSize(true)


        eventModelArrayList = arrayListOf<EventModel>()
        getEventData()

        haIvProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        haBtChat.setOnClickListener {
            startActivity(Intent(this@UserHomeActivity, ChatActivity::class.java))
        }

        haBtCreateTeam.setOnClickListener {
            startActivity(Intent(this@UserHomeActivity, CreateTeamActivity::class.java))
        }

        haBtJoinTeam.setOnClickListener {
            startActivity(Intent(this@UserHomeActivity, JoinTeamActivity::class.java))
        }


    }

    private fun getEventData() {
        dbref = FirebaseDatabase.getInstance().getReference("Events")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (eventSnapshot in snapshot.children) {

                        val event = eventSnapshot.getValue(EventModel::class.java)
                        eventModelArrayList.add(event!!)
                    }

                    eventRecyclerview.adapter = RecyclerviewAdapter(eventModelArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}
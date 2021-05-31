package com.example.geeksearch.user.user

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geeksearch.databinding.ActivityUserHomeBinding
import com.example.geeksearch.user.announcement.AnnouncementActivity
import com.example.geeksearch.user.event.EventActivity
import com.example.geeksearch.user.user.profile.UserProfileActivity

class UserHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val haIvProfile = findViewById<ImageView>(R.id.haIvProfile)
//        val haBtChat = findViewById<Button>(R.id.haBtChat)
//        val haBtCreateTeam = findViewById<Button>(R.id.haBtCreateTeam)
//        val haBtJoinTeam = findViewById<Button>(R.id.haBtJoinTeam)

        binding.imgBtnUserProfile.setOnClickListener {
            startActivity(Intent(this, UserProfileActivity::class.java))
        }

        binding.btnUserHomeActivityEvent.setOnClickListener {
            startActivity(Intent(this, EventActivity::class.java))
        }
        binding.btnUserHomeActivityAnnouncement.setOnClickListener {
            startActivity(Intent(this, AnnouncementActivity::class.java))
        }
    }

}
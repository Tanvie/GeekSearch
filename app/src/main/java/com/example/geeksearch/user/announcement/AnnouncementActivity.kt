package com.example.geeksearch.user.announcement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geeksearch.databinding.ActivityAnnouncementBinding

class AnnouncementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnnouncementBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnnouncementBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
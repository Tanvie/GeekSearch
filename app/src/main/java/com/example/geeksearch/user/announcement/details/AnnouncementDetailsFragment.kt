package com.example.geeksearch.user.announcement.details

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.geeksearch.databinding.FragmentAnnouncementDetailsBinding
import com.example.geeksearch.user.ViewUserProfileActivity
import com.example.geeksearch.user.announcement.announcement.AnnouncementModel
import de.cketti.mailto.EmailIntentBuilder


class AnnouncementDetailsFragment : Fragment() {
    private var _binding: FragmentAnnouncementDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnnouncementDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        val arguments = arguments?.let { AnnouncementDetailsFragmentArgs.fromBundle(it) }
        val ann: AnnouncementModel? = arguments.Announcemnets

        if (ann != null) {
            binding.tvAnnDetailsHackName.text = ann.hackName
            binding.tvAnnDetailsHackDomain.text = ann.hackDomain
            binding.tvAnnDetailsHackEnd.text = ann.hackEnd
            binding.tvAnnDetailsHackStart.text = ann.hackStart
            binding.tvAnnDetailsHackMessage.text = ann.message
            binding.tvAnnDetailsHackRequirements.text = ann.requirements
            binding.tvAnnDetailsHackUserEmail.text = ann.userEmail
            binding.tvAnnDetailsHackUserName.text = ann.userName
        }

        binding.btnMailTo.setOnClickListener {
            val success: Boolean = ann?.let { it1 ->
                activity?.let { it2 ->
                    EmailIntentBuilder.from(it2)
                        .to(it1.userEmail)
                        .subject("About Announcement made on GeekSearch about: " + ann.hackName)
                        .body("Hello")
                        .start()
                }
            } == true
        }

        binding.btnViewUserProfile.setOnClickListener {
            val intent = Intent(activity, ViewUserProfileActivity::class.java)
            if (ann != null) {
                intent.putExtra("inviterEmail", ann.userEmail)
                intent.putExtra("hack_name", ann.hackName)

            }
            startActivity(intent)
        }
        return view
    }
}
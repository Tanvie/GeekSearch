package com.example.geeksearch.user.event.details

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.geeksearch.databinding.FragmentEventDetailsBinding
import com.example.geeksearch.user.announcement.create.CreateAnnouncementActivity
import com.example.geeksearch.user.event.event.EventModel

class EventDetailsFragment : Fragment() {
    private var _binding: FragmentEventDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        val arguments = arguments?.let { EventDetailsFragmentArgs.fromBundle(it) }
        val st: EventModel? = arguments?.Event

        if (st != null) {
            binding.tvEventFragDetailsName.text = st.hckName
            binding.tvEventFragDomain.text = st.domain
            binding.tvEventFragLink.text = st.link
            binding.tvEventFragDescription.text = st.hckDes
            binding.tvEventFragRegStart.text = st.regOpenDate
            binding.tvEventFragRegEnds.text = st.regCloseDate
            binding.tvEventFragHckStart.text = st.hckStart
            binding.tvEventFragHckEnds.text = st.hckEnd
            binding.tvEventFragEligibility.text = st.eligibity
            binding.tvEventFragPrizes.text = st.prizes
        }

        binding.btnAnnouncement.setOnClickListener {
            val intent = Intent(activity, CreateAnnouncementActivity::class.java)
            startActivity(intent)
        }
        return view
    }
}
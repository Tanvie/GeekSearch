package com.example.geeksearch.user.announcement.announcement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geeksearch.databinding.FragmentAnnouncementBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class AnnouncementFragment : Fragment(), AnnouncementAdapter.OnEventClickListener {

    private lateinit var adapter: AnnouncementAdapter
    private var _binding: FragmentAnnouncementBinding? = null
    private lateinit var options: FirestoreRecyclerOptions<AnnouncementModel>

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnnouncementBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        // Realtime purpose
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        // Realtime purpose
        adapter.stopListening()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val query: Query = FirebaseFirestore.getInstance()
            .collection("Announcements")

        options = FirestoreRecyclerOptions.Builder<AnnouncementModel>()
            .setQuery(query, AnnouncementModel::class.java)
            .build()

        // Setting up Adapter and RecyclerView
        setupParentList()
    }

    private fun setupParentList() {
        adapter = AnnouncementAdapter(this, options)
        binding.rvAnnouncement.layoutManager = LinearLayoutManager(activity)
        binding.rvAnnouncement.adapter = adapter
    }

    override fun onEventClick(announcement: AnnouncementModel) {
        val action = AnnouncementFragmentDirections
            .actionAnnouncementFragmentToAnnouncementDetailsFragment(announcement)
        NavHostFragment.findNavController(this).navigate(action)
    }
}
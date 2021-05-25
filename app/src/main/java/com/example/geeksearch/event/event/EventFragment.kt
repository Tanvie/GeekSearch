package com.example.geeksearch.event.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geeksearch.databinding.FragmentEventBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class EventFragment : Fragment(), EventAdapter.OnEventClickListener {

    private lateinit var adapter: EventAdapter
    private var _binding: FragmentEventBinding? = null
    private lateinit var options: FirestoreRecyclerOptions<EventModel>

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventBinding.inflate(inflater, container, false)

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
            .collection("Events")

        options = FirestoreRecyclerOptions.Builder<EventModel>()
            .setQuery(query, EventModel::class.java)
            .build()

        // Setting up Adapter and RecyclerView
        setupParentList()
    }

    private fun setupParentList() {
        adapter = EventAdapter(this, options)
        binding.rvFirestoreItems.layoutManager = LinearLayoutManager(activity)
        binding.rvFirestoreItems.adapter = adapter
    }

    override fun onEventClick(Event: EventModel) {
        val action = EventFragmentDirections
            .actionEventFragmentToEventDetailsFragment2(Event)
        NavHostFragment.findNavController(this).navigate(action)
    }
}
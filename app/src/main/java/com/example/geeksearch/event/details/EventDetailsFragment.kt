package com.example.geeksearch.event.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.geeksearch.databinding.FragmentEventDetailsBinding
import com.example.geeksearch.event.event.EventModel

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
            binding.name.text = st.name
            binding.city.text = st.city
            binding.state.text = st.state
        }
        return view
    }
}
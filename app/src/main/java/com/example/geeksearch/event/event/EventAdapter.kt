package com.example.geeksearch.event.event

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.geeksearch.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

/**
 * Created by Tanvi Wakade on 28-04-2021.
 */
class EventAdapter(
    private val onEventClickListener: OnEventClickListener,
    private val recyclerOptions: FirestoreRecyclerOptions<EventModel>
) : FirestoreRecyclerAdapter<EventModel, EventAdapter.EventViewHolder>(recyclerOptions) {


    inner class EventViewHolder(
        @NonNull itemView: View,
        onEventClickListener: OnEventClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val name: TextView
        private val city: TextView
        private val state: TextView
        private val onEventClickListener: OnEventClickListener

        init {
            name = itemView.findViewById(R.id.tv_name_of_orphanage_firestore)
            city = itemView.findViewById(R.id.tv_city_of_orphanage_firestore)
            state = itemView.findViewById(R.id.tv_state_of_orphanage_firestore)

            this.onEventClickListener = onEventClickListener

            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            onEventClickListener.onEventClick(getItem(absoluteAdapterPosition))
        }

        fun bindEventData(eventModel: EventModel) {
            name.text = eventModel.name
            city.text = eventModel.city
            state.text = eventModel.state
        }
    }

    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): EventAdapter.EventViewHolder {
        Log.d("Adapter", "onCreateViewHolder()")

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView =
            layoutInflater.inflate(R.layout.recycler_event_list_items, parent, false)
        return EventViewHolder(itemView, this.onEventClickListener)
    }

    override fun onBindViewHolder(
        holder: EventViewHolder,
        position: Int,
        model: EventModel
    ) {
        Log.d("Adapter", "onBindViewHolder() for position $position")

        holder.bindEventData(model)
    }

    override fun getItemCount(): Int {
        return recyclerOptions.snapshots.size
    }

    interface OnEventClickListener {
        fun onEventClick(Event: EventModel)
    }

    companion object {
        private const val TAG = "Firestore Adapter"
    }
}
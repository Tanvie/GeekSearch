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
        private val hckName: TextView
        private val domain: TextView
        private val link: TextView
        private val onEventClickListener: OnEventClickListener

        init {
            hckName = itemView.findViewById(R.id.tv_event_list_name)
            domain = itemView.findViewById(R.id.tv_event_domain)
            link = itemView.findViewById(R.id.tv_event_link)

            this.onEventClickListener = onEventClickListener

            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            onEventClickListener.onEventClick(getItem(absoluteAdapterPosition))
        }

        fun bindEventData(eventModel: EventModel) {
            hckName.text = eventModel.hckName
            domain.text = eventModel.domain
            link.text = eventModel.link
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
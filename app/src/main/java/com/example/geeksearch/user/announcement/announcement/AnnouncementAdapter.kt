package com.example.geeksearch.user.announcement.announcement

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


class AnnouncementAdapter(
    private val onEventClickListener: AnnouncementFragment,
    private val recyclerOptions: FirestoreRecyclerOptions<AnnouncementModel>
) : FirestoreRecyclerAdapter<AnnouncementModel, AnnouncementAdapter.AnnouncementViewHolder>(
    recyclerOptions
) {


    inner class AnnouncementViewHolder(
        @NonNull itemView: View,
        onEventClickListener: OnEventClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val eventUserName: TextView
        private val hackName: TextView
        private val onEventClickListener: OnEventClickListener

        init {
            eventUserName = itemView.findViewById(R.id.tv_event_list_user_name)
            hackName = itemView.findViewById(R.id.tv_hack_name)

            this.onEventClickListener = onEventClickListener

            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            onEventClickListener.onEventClick(getItem(absoluteAdapterPosition))
        }

        fun bindEventData(announcementModel: AnnouncementModel) {
            eventUserName.text = announcementModel.userName
            hackName.text = announcementModel.hackName
        }
    }

    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): AnnouncementAdapter.AnnouncementViewHolder {
        Log.d("Adapter", "onCreateViewHolder()")

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView =
            layoutInflater.inflate(R.layout.layout_an_list_items, parent, false)
        return AnnouncementViewHolder(itemView, this.onEventClickListener)
    }

    override fun onBindViewHolder(
        holder: AnnouncementAdapter.AnnouncementViewHolder,
        position: Int,
        model: AnnouncementModel
    ) {
        Log.d("Adapter", "onBindViewHolder() for position $position")

        holder.bindEventData(model)
    }

    override fun getItemCount(): Int {
        return recyclerOptions.snapshots.size
    }

    interface OnEventClickListener {
        fun onEventClick(Event: AnnouncementModel)
    }

    companion object {
        private const val TAG = "Firestore Adapter"
    }
}
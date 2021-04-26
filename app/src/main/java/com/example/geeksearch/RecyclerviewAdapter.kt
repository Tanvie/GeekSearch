package com.example.geeksearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerviewAdapter(
    private val eventModelList: ArrayList<EventModel>,
    private val listener: com.google.firebase.database.ValueEventListener
) :
    RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = eventModelList[position]
        holder.hckTitle.text = currentItem.hckname
        holder.hckDomain.text = currentItem.domain
    }

    override fun getItemCount(): Int {
        return eventModelList.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val hckTitle: TextView = itemView.findViewById(R.id.ha_rv_hck_title)
        val hckDomain: TextView = itemView.findViewById(R.id.ha_rv_hck_domain)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface ValueEventListener {
        fun onItemClick(position: Int)
    }
}

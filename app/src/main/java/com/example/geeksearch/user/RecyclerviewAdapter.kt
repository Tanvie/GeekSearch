package com.example.geeksearch.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.geeksearch.EventModel
import com.example.geeksearch.R

class RecyclerviewAdapter(private val eventModelList: ArrayList<EventModel>) :
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


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hckTitle: TextView = itemView.findViewById(R.id.ha_rv_hck_title)
        val hckDomain: TextView = itemView.findViewById(R.id.ha_rv_hck_domain)


    }
}
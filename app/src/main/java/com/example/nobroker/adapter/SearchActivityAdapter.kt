package com.example.nobroker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nobroker.R
import com.example.nobroker.data.database.NoBrokerDataEntity
import com.example.nobroker.viewholder.SearchActivityViewHolder

class SearchActivityAdapter(val detailsList: MutableList<NoBrokerDataEntity>) :
    RecyclerView.Adapter<SearchActivityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchActivityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_activity_item_layout, parent, false)
        return SearchActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchActivityViewHolder, position: Int) {

        Glide.with(holder.image).load(detailsList[position].image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return detailsList.size
    }
}
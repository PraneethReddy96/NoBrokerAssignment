package com.example.nobroker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nobroker.R
import com.example.nobroker.data.database.NoBrokerDataEntity
import com.example.nobroker.utils.onItemClickListener
import com.example.nobroker.viewholder.SearchActivityViewHolder
import com.example.nobroker.views.SearchActivity

class SearchActivityAdapter(
    val detailsList: MutableList<NoBrokerDataEntity>,
    val itemClickListener: onItemClickListener,

) :
    RecyclerView.Adapter<SearchActivityViewHolder>(),Filterable {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchActivityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_activity_item_layout, parent, false)
        return SearchActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchActivityViewHolder, position: Int) {

        Glide.with(holder.ivImage).load(detailsList[position].image).into(holder.ivImage)
        holder.tvTitle.text = detailsList[position].title
        holder.tvSubTitle.text = detailsList[position].subTitle
        holder.rlContainer.setOnClickListener(View.OnClickListener {

            itemClickListener.onItemClicked(detailsList[position])

        })
    }

    override fun getItemCount(): Int {
        return detailsList.size
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
}
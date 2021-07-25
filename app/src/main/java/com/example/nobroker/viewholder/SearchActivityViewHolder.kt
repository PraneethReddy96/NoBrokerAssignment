package com.example.nobroker.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nobroker.R

class SearchActivityViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    /* initializing the views from the item layout*/
    var ivImage = view.findViewById<ImageView>(R.id.ivSearchImage)
    var tvTitle = view.findViewById<TextView>(R.id.tvSearchTitle)
    var tvSubTitle = view.findViewById<TextView>(R.id.tvSearchDescription)
    var rlContainer = view.findViewById<RelativeLayout>(R.id.rlSearchContainer)


}
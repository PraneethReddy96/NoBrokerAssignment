package com.example.nobroker.utils

import com.example.nobroker.data.database.NoBrokerDataEntity

interface onItemClickListener {

    fun onItemClicked(entity : NoBrokerDataEntity)
}
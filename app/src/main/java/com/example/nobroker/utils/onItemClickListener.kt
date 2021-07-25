package com.example.nobroker.utils

import com.example.nobroker.data.database.NoBrokerDataEntity

interface onItemClickListener {
    /* used for fetching the entity from a certain position*/
    fun onItemClicked(entity: NoBrokerDataEntity)
}
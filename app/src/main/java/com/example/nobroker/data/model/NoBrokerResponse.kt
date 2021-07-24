package com.example.nobroker.data.model

import com.google.gson.annotations.SerializedName



data class NoBrokerResponseItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("subTitle")
    val subTitle: String? = null,

    @field:SerializedName("title")
    val title: String? = null
)

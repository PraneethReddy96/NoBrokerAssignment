package com.example.nobroker.data.remote


import com.example.nobroker.data.model.NoBrokerResponseItem
import retrofit2.http.GET

interface ApiClient {

    @GET("/b/60fa8fefa917050205ce5470")
    suspend  fun getDetails(): MutableList<NoBrokerResponseItem?>



}
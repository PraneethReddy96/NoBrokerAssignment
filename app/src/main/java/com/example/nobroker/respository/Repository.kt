package com.example.nobroker.respository

import ApiClient
import androidx.lifecycle.liveData
import com.example.nobroker.data.database.NoBrokerDao
import com.example.nobroker.data.model.NoBrokerResponseItem
import kotlinx.coroutines.Dispatchers

class Repository(val noBrokerDao: NoBrokerDao) {

     val apiClient = RetrofitGenerator.getInstance().create(ApiClient::class.java)
     val requestHandler = RetrofitNetworkRequestHandler.ResponseHandler()


//     suspend fun getData(): List<NoBrokerResponseItem>{
//
//         var response = apiClient.getDetails()
//
//         retur
//
//     }




}
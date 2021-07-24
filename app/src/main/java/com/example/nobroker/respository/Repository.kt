package com.example.nobroker.respository


import androidx.lifecycle.LiveData
import com.example.nobroker.data.database.NoBrokerDao
import com.example.nobroker.data.database.NoBrokerDataEntity
import com.example.nobroker.data.model.NoBrokerResponseItem
import com.example.nobroker.data.remote.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(val noBrokerDao: NoBrokerDao) {

    val apiClient = RetrofitGenerator.getInstance().create(ApiClient::class.java)
    val requestHandler = RetrofitNetworkRequestHandler.ResponseHandler()





    suspend fun getData() : RetrofitNetworkRequestHandler.Resource<MutableList<NoBrokerResponseItem?>> {

        var response = apiClient.getDetails()

        try {
            return requestHandler.handleSuccess(response)

        } catch (e: Exception) {
            return requestHandler.handleException(e)
        }

    }



    fun getNoBrokerEntity(): LiveData<MutableList<NoBrokerDataEntity>> {


        return noBrokerDao.getData()

    }




}
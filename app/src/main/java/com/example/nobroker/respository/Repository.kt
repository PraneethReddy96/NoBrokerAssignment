package com.example.nobroker.respository


import androidx.lifecycle.LiveData
import com.example.nobroker.data.database.NoBrokerDao
import com.example.nobroker.data.database.NoBrokerDataBase
import com.example.nobroker.data.database.NoBrokerDataEntity
import com.example.nobroker.data.model.NoBrokerResponseItem
import com.example.nobroker.data.remote.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(val noBrokerDao: NoBrokerDao) {

    val apiClient = RetrofitGenerator.getInstance().create(ApiClient::class.java)
    val requestHandler = RetrofitNetworkRequestHandler.ResponseHandler()


    /*
    Fetches the data from Response class through apiClient , handles  through request handler and returns the response.
     */
    suspend fun getData(): RetrofitNetworkRequestHandler.Resource<MutableList<NoBrokerResponseItem?>> {

        var response = apiClient.getDetails()

        try {
            return requestHandler.handleSuccess(response)

        } catch (e: Exception) {
            return requestHandler.handleException(e)
        }

    }


    /* function to check whether database is empty , returns 1 if true, else 0*/
    fun checkData(): Int {
        var check = 0
        var data: LiveData<NoBrokerDataEntity?>? = noBrokerDao.loadLastTask()
        if (data == null) {

            check = 1
        }
        return check
    }



    /*
    Returns mutable Live Data from the Dao interface, by using noBrokerDao object
     */
    fun getNoBrokerEntity(): LiveData<MutableList<NoBrokerDataEntity>> {


        return noBrokerDao.getData()

    }


}
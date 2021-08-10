package com.example.nobroker.respository

import RetrofitNetworkRequestHandler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.nobroker.data.database.NoBrokerDao
import com.example.nobroker.data.database.NoBrokerDataEntity
import com.example.nobroker.data.remote.ApiClient
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(val apiClient: ApiClient, val noBrokerDao: NoBrokerDao) {





    /*
    Fetches the data from Response class through apiClient , handles  through request handler and returns the response.
     */
    suspend fun getData() {

        var response = apiClient.getDetails()

//        try {
//            requestHandler.handleSuccess(response)
//
//        } catch (e: Exception) {
//            requestHandler.handleException(e)
//        }


        liveData(Dispatchers.IO) {

            emit(response)
        }

        for (i in response?.indices!!) {
            var NoBrokerDataEntity =
                NoBrokerDataEntity(response?.get(i)?.image,
                    response?.get(i)?.title,
                    response?.get(i)?.subTitle)

            noBrokerDao.insertData(NoBrokerDataEntity)
        }


    }


    /* function to check whether database is empty , returns 1 if true, else 0*/
    fun checkData(): Int {
        var check = 0

        if (noBrokerDao.loadLastTask() == null) {

            check = 1
        }


        Log.d("TAG", check.toString())
        return check
    }


    /*
    Returns mutable Live Data from the Dao interface, by using noBrokerDao object
     */
    fun getNoBrokerEntity(): LiveData<MutableList<NoBrokerDataEntity>> {


        return noBrokerDao.getData()

    }


}
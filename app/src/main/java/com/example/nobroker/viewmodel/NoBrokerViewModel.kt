package com.example.nobroker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nobroker.data.database.NoBrokerDataEntity
import com.example.nobroker.data.model.NoBrokerResponseItem
import com.example.nobroker.respository.Repository
import kotlinx.coroutines.Dispatchers


class NoBrokerViewModel(val repository: Repository) : ViewModel() {


    /* invokes the addition of data into entity while fetching back the response from the repository */
    fun addToDataBase(): LiveData<MutableList<NoBrokerResponseItem?>?> {

        return liveData(Dispatchers.IO) {
            val response = repository.getData()
            emit(response?.data)

        }
    }


    /* retrieves the data whether data base is empty , and passes on to the subscribers*/
    fun checkDataBase(): LiveData<Int> {

        return liveData(Dispatchers.IO) {
            val check = repository.checkData()
            emit(check)
        }


    }


    /* retrieves the  list of entity, and waits for being subscribed */
    fun retrieveNoBrokerDataEntity(): LiveData<MutableList<NoBrokerDataEntity>> {

        return repository.getNoBrokerEntity()
    }


}
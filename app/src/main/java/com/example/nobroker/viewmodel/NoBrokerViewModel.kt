package com.example.nobroker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nobroker.data.database.NoBrokerDataEntity
import com.example.nobroker.respository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoBrokerViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {


    /* invokes the addition of data into entity while fetching back the response from the repository */
    fun addToDataBase(){

        CoroutineScope(Dispatchers.IO).launch {

            repository.getData()
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
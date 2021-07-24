package com.example.nobroker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nobroker.data.database.NoBrokerDataEntity
import com.example.nobroker.data.model.NoBrokerResponseItem
import com.example.nobroker.respository.Repository
import kotlinx.coroutines.Dispatchers


class NoBrokerViewModel(val repository: Repository) : ViewModel() {




    fun addToDataBase() : LiveData<MutableList<NoBrokerResponseItem?>?>{

        return liveData(Dispatchers.IO){
                val response = repository.getData()
                emit(response?.data)

            }
    }



    fun checkDataBase() : Int{

       val check = repository.checkData()

        return check

    }



    fun retrieveNoBrokerDataEntity() : LiveData<MutableList<NoBrokerDataEntity>>{

        return repository.getNoBrokerEntity()
    }


}
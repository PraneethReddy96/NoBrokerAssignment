package com.example.nobroker.utils

import android.app.Application
import com.example.nobroker.data.database.NoBrokerDataBase
import com.example.nobroker.respository.Repository

class MyApplication : Application() {

    /* creating a repository instance by lazy , and passing the Dao object such that it can be utilized
    whenever required throughout the application */
    val repository by lazy {

        val noBrokerDao = NoBrokerDataBase.getNewsArticlesDatabase(this).getNoBrokerDao()
        Repository(noBrokerDao)

    }

}
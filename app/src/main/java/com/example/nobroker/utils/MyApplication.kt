package com.example.nobroker.utils

import android.app.Application
import com.example.nobroker.data.database.NoBrokerDataBase
import com.example.nobroker.respository.Repository

class MyApplication : Application() {

    val repository by lazy {

        val noBrokerDao = NoBrokerDataBase.getNewsArticlesDatabase(this).getNoBrokerDao()
        Repository(noBrokerDao)

    }

}
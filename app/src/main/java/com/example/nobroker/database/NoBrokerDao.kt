package com.example.nobroker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoBrokerDao {

    @Insert
    fun insertData(noBrokerDataEntity: NoBrokerDataEntity)

    @Query(value ="select * from NoBroker")
    fun getData(): LiveData<List<NoBrokerDataEntity>>


}
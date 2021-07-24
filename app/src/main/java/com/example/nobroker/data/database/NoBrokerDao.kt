package com.example.nobroker.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoBrokerDao {

    @Insert
    fun insertData(noBrokerDataEntity: NoBrokerDataEntity)

    @Query(value ="select * from NoBroker order by id asc")
    fun getData(): LiveData<MutableList<NoBrokerDataEntity>>


    @Query("SELECT * FROM NoBroker ORDER BY id LIMIT 1")
    fun loadLastTask(): LiveData<NoBrokerDataEntity?>?

}
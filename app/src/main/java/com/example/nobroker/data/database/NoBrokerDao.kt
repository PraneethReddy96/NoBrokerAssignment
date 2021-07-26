package com.example.nobroker.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoBrokerDao {

    /* Function to Insert data into the table  */
    @Insert
    fun insertData(noBrokerDataEntity: NoBrokerDataEntity)

    /* Function which returns the list of elements present in the table */
    @Query(value ="select * from NoBroker order by id asc")
    fun getData(): LiveData<MutableList<NoBrokerDataEntity>>

    /* Function to check whether the table is empty */
    @Query("SELECT * FROM NoBroker ORDER BY id LIMIT 1")
    fun loadLastTask(): NoBrokerDataEntity

}
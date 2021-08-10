package com.example.nobroker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [NoBrokerDataEntity::class], version = 1, exportSchema = false)
abstract class NoBrokerDataBase : RoomDatabase() {

    /* used with the db instance to perform operations on database */
    abstract fun getNoBrokerDao(): NoBrokerDao


}
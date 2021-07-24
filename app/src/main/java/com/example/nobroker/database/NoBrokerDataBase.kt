package com.example.nobroker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoBrokerDataEntity::class], version = 1)
abstract class NoBrokerDataBase : RoomDatabase() {

    abstract fun getNoBrokerDao(): NoBrokerDao

    companion object {

        var INSTANCE: NoBrokerDataBase? = null

        fun getNewsArticlesDatabase(context: Context): NoBrokerDataBase {

            if (INSTANCE == null) {

                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    NoBrokerDataBase::class.java,
                    "NoBroker_DataBase"
                )

                INSTANCE = builder.build()

                return INSTANCE!!

            } else

                return INSTANCE!!
        }
    }
}
package com.example.nobroker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoBrokerDataEntity::class], version = 1)
abstract class NoBrokerDataBase : RoomDatabase() {

    abstract fun getNoBrokerDao(): NoBrokerDao

    companion object {

        @Volatile
        var INSTANCE: NoBrokerDataBase? = null
        val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {

            INSTANCE ?: getNewsArticlesDatabase(context).also { INSTANCE = it }

        }


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
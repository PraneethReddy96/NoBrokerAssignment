package com.example.nobroker.di

import android.content.Context
import androidx.room.Room
import com.example.nobroker.data.database.NoBrokerDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NoBrokerDataBase = Room.databaseBuilder(
        context,
        NoBrokerDataBase::class.java,
        "NoBroker_DataBase"
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: NoBrokerDataBase) = database.getNoBrokerDao()


}
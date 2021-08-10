package com.example.nobroker.di

import com.example.nobroker.data.remote.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MyModule {

    @Provides
    @Singleton
    fun getInstance(): Retrofit {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io")
            .addConverterFactory(GsonConverterFactory.create())

        return retrofitBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(retrofit: Retrofit) =  retrofit.create(ApiClient::class.java)





}
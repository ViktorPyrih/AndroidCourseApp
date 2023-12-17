package com.example.lab3.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object StoreHelper {
    val baseUrl = "http://10.0.2.2:3034/dev/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}
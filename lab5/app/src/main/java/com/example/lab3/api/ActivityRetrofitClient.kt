package com.example.lab3.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ActivityRetrofitClient {
    private val URL = "https://www.boredapi.com/api/"

    fun getClient(): Retrofit =
        Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

}
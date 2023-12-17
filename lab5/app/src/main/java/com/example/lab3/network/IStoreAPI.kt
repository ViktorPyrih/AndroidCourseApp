package com.example.lab3.network

import com.example.lab3.models.Product
import com.example.lab3.models.Staff
import retrofit2.Response
import retrofit2.http.GET

interface IStoreAPI {
    @GET("products")
    suspend fun getProducts(): Response<Array<Product>>

    @GET("staff")
    suspend fun getStaff(): Response<Array<Staff>>
}
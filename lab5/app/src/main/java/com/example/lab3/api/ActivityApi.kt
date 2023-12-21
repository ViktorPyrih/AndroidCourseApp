package com.example.lab3.api

import com.example.lab3.models.ActivityEntity
import retrofit2.Response
import retrofit2.http.GET

interface ActivityApi {
    @GET("activity")
    suspend fun getActivity(): Response<ActivityEntity>
}
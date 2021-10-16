package com.example.myapplication.data.network

import com.example.myapplication.data.ServerResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    companion object{
        const val BASE_URL = "https://web.jcorp.com.my/"
    }

    @GET("hcmsapi/leave/type/available")
    suspend fun getDetails():Response<ServerResponse>

}
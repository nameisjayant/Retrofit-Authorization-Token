package com.example.myapplication.data.repository

import com.example.myapplication.data.network.ApiService
import com.example.myapplication.utils.toResultFlow
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    fun getDetails() = toResultFlow {
        apiService.getDetails()
    }

}
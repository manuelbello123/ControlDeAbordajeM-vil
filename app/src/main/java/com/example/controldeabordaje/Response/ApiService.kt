package com.example.controldeabordaje.Response

import androidx.compose.runtime.MutableState
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST (value = "login" )
    fun  postLogin(
        @Query(value = "email") email: MutableState<String>,
        @Query(value = "password") password: MutableState<String>
    ):
            Call <LoginResponse>

    companion object Factory{
        private const val BASE_URL = "http://187.188.213.206:18081/api/"
        fun create(): ApiService{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}
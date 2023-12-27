package com.example.weatherapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{
        private var apiServiceMain: IApiService? = null
        private var apiServiceBackup: IApiService? = null
        fun getInstanceMain(): IApiService{
            if(apiServiceMain == null){
                apiServiceMain = Retrofit.Builder()
                    .baseUrl("https://pro.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(IApiService::class.java)
            }
            return apiServiceMain!!
        }
        fun getInstanceBackup(): IApiService{
            if(apiServiceBackup == null){
                apiServiceBackup = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(IApiService::class.java)
            }
            return apiServiceBackup!!
        }
    }
}
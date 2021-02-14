package com.example.gazarshop.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitModule {
    fun  getClient() : FakeAPI{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://fakestoreapi.com/")
                .build()
                .create(FakeAPI::class.java)

    }
}
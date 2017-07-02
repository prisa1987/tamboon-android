package com.omise.tamboon.data.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class TamboonNetwork {

    companion object {
        var gson = GsonBuilder()
                .setLenient()
                .create()

        var api = Retrofit.Builder()
                .baseUrl("http://192.168.1.33:9000/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(TamboonApi::class.java)
    }
}
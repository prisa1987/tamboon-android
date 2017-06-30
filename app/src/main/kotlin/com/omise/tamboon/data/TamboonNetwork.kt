package com.omise.tamboon.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TamboonNetwork {

    companion object {
        var api = Retrofit.Builder()
                .baseUrl("http://www.mocky.io/v2/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TamboonApi::class.java)
    }
}
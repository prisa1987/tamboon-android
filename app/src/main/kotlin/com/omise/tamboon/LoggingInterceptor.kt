package com.omise.tamboon

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val time = System.nanoTime()
        Log.d("network", "request ==> ${request.url()} ${chain.connection()} ${request.headers()}")

        val response = chain.proceed(request)
        Log.d("network", "response ==> ${request.url()} ${chain.connection()} ${response.headers()} \n body: ${response.body()}")
        return response
    }

}
package com.omise.tamboon.data

import com.omise.tamboon.data.entity.Charity
import io.reactivex.Single
import retrofit2.http.GET

interface TamboonApi {

    @GET("595534f40f0000fa03fc0fa2")
    fun getAllCharities() : Single<List<Charity>>

}
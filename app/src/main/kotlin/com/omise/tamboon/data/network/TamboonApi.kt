package com.omise.tamboon.data.network

import com.omise.tamboon.data.entity.Charge
import com.omise.tamboon.data.entity.Charity
import com.omise.tamboon.data.entity.User
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TamboonApi {

    @GET("charities")
    fun getAllCharities(): Single<List<Charity>>

    @GET("user")
    fun getUserInfo(): Single<User>

    @POST("charities/{id}/donate")
    fun donateCharity(@Path("id") charityId: String, @Body body: RequestBody): Single<Charge>

}
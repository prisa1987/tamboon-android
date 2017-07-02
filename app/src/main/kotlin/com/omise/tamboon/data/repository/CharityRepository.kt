package com.omise.tamboon.data.repository

import com.google.gson.Gson
import com.omise.tamboon.data.network.TamboonNetwork
import com.omise.tamboon.data.entity.Charge
import com.omise.tamboon.data.entity.Charity
import com.omise.tamboon.data.entity.Donation
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody


interface CharityRepository {

    fun getCharities(): Single<List<Charity>>
    fun donateCharity(charityId: String, token: String, cardName: String, amount: Double): Single<Charge>

}

class CharityRepositoryImpl : CharityRepository {

    override fun getCharities(): Single<List<Charity>> {
        return TamboonNetwork.api.getAllCharities().subscribeOn(Schedulers.io())
    }

    override fun donateCharity(charityId: String, token: String, cardName: String, amount: Double): Single<Charge> {
        val donation = Donation(cardName, token, amount)
        val JSON = MediaType.parse("application/json; charset=utf-8")
        return TamboonNetwork.api.donateCharity(charityId, RequestBody.create(JSON, Gson().toJson(donation))).subscribeOn(Schedulers.io())
    }

}
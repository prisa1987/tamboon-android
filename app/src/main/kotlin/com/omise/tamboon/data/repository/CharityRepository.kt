package com.omise.tamboon.data.repository

import com.omise.tamboon.data.TamboonNetwork
import com.omise.tamboon.data.entity.Charity
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


interface CharityRepository {

    fun getCharities(): Single<List<Charity>>

}

class CharityRepositoryImpl : CharityRepository {

    override fun getCharities(): Single<List<Charity>> {
        return TamboonNetwork.api.getAllCharities().subscribeOn(Schedulers.io())
    }

}
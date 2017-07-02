package com.omise.tamboon.data.repository

import com.omise.tamboon.data.network.TamboonNetwork
import com.omise.tamboon.data.entity.User
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface UserRepository {

    fun getUserInfo(): Single<User>

}

class UserRepositoryImpl : UserRepository {

    override fun getUserInfo(): Single<User> {
        return TamboonNetwork.api.getUserInfo().subscribeOn(Schedulers.io())
    }

}
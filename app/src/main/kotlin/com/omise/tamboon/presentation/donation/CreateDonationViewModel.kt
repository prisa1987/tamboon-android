package com.omise.tamboon.presentation.donation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.gson.Gson
import com.omise.tamboon.data.entity.ResponseError
import com.omise.tamboon.data.entity.User
import com.omise.tamboon.data.extension.untilDestory
import com.omise.tamboon.data.repository.CharityRepositoryImpl
import com.omise.tamboon.data.repository.UserRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.HttpException

class CreateDonationViewModel : ViewModel() {

    lateinit var viewAction: CreateDonationViewAction
    private var useRepository = UserRepositoryImpl()
    private var charityRepository = CharityRepositoryImpl()

    private val userLiveData: MutableLiveData<User> = MutableLiveData()

    fun getUserInfo(): MutableLiveData<User> {
        useRepository.getUserInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { success, error ->
                    success?.let {
                        userLiveData.value = it
                    }

                    error?.let {
                        viewAction.alertError(message = it.message ?: "")
                    }
                }.untilDestory()
        return userLiveData
    }

    fun payAmount(charityId: String, amount: Double) {
        charityRepository
                .donateCharity(charityId, userLiveData.value?.tokenId ?: "", userLiveData.value?.card?.name ?: "", amount)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { success, error ->
                    success?.let {
                        viewAction.alertDonateSucess(it.card.name, (it.amount / 100).toString())
                    }

                    error?.let {
                        when (it) {
                            is HttpException -> it.response().errorBody()?.string()?.let {
                                val responseError = Gson().fromJson(it, ResponseError::class.java)
                                responseError?.let {
                                    if (!it.message.isNullOrBlank()) {
                                        viewAction.alertError(message = it.message)
                                    }
                                }
                            }
                            else -> viewAction.alertError(message = "technical issue")
                        }
                    }
                }
    }

}
package com.omise.tamboon.presentation.charities

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.omise.tamboon.data.entity.Charity
import com.omise.tamboon.data.repository.CharityRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers

class CharitiesViewModel : ViewModel() {

    var repository = CharityRepositoryImpl()

    private val liveData: MutableLiveData<List<Charity>> = MutableLiveData()

    fun getCharities(): MutableLiveData<List<Charity>> {
        repository.getCharities()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { data -> liveData.value = data }
        return liveData
    }
}
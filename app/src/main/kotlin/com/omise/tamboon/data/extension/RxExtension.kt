package com.omise.tamboon.data.extension

import io.reactivex.disposables.Disposable
import io.reactivex.disposables.CompositeDisposable

val compositeDisposable = CompositeDisposable()

fun Disposable.untilDestory() {
    compositeDisposable.add(this)
}
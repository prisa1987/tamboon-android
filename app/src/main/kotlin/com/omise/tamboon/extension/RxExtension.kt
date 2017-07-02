package com.omise.tamboon.extension

import io.reactivex.disposables.Disposable
import io.reactivex.disposables.CompositeDisposable

val compositeDisposable = CompositeDisposable()

fun Disposable.untilDestory() {
    compositeDisposable.add(this)
}
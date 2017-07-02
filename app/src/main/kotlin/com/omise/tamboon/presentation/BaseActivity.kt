package com.omise.tamboon.presentation

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.view_toolbar.*
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity : AppCompatActivity(), LifecycleRegistryOwner, BaseViewAction {

    protected abstract val contentLayoutResourceId: Int
    protected abstract val toolbarTitle: String
    private val compositeDisposable = CompositeDisposable()

    lateinit var registry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(contentLayoutResourceId)
        registry = LifecycleRegistry(this)

        //handle savedInstanceState
        savedInstanceState?.let {
            handleSavedInstanceState(it)
        }

        //handle intent
        intent?.let {
            handleIntent(it)
        }

        setUpToolbar()
        setUp()
    }

    override fun getLifecycle() = registry

    open fun handleSavedInstanceState(savedInstanceState: Bundle) {}
    open fun handleIntent(intent: Intent) {}
    open fun setUp() {}

    fun setUpToolbar() {
        tb?.let {
            it.title = toolbarTitle
            setSupportActionBar(it)
        }
    }

    override fun alertError(title: String, message: String) {
        val alert = AlertDialog.Builder(this)
        alert.setTitle(title)
        alert.setMessage(message)
        alert.setCancelable(true)
        alert.setPositiveButton("OK") { dialog, id -> dialog.dismiss() }
        alert.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onBack() {
        finish()
    }

}
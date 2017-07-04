package com.omise.tamboon.presentation

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.omise.android.tamboon.R
import com.omise.tamboon.extension.compositeDisposable
import kotlinx.android.synthetic.main.view_toolbar.*

abstract class BaseActivity : AppCompatActivity(), LifecycleRegistryOwner, BaseViewAction {

    protected abstract val contentLayoutResourceId: Int
    protected abstract val toolbarTitle: String

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
        val alert = AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            setCancelable(true)
            setPositiveButton(getString(R.string.R_string_ok)) { dialog, _ -> dialog.dismiss() }
        }
        alert.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun onBack() {
        finish()
    }

}
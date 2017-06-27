package com.omise.tamboon.presentation

import android.arch.lifecycle.LifecycleActivity
import android.content.Intent
import android.os.Bundle

abstract class BaseActivity : LifecycleActivity() {

    abstract val contentLayoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(contentLayoutResourceId)

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

    open fun handleSavedInstanceState(savedInstanceState: Bundle) {}
    open fun handleIntent(intent: Intent) {}
    open fun setUpToolbar() {}
    open fun setUp() {}

}
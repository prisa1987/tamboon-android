package com.omise.tamboon.presentation

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.view_toolbar.*

abstract class BaseActivity : AppCompatActivity(), LifecycleRegistryOwner {

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

}
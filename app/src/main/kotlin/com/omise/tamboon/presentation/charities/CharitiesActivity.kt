package com.omise.tamboon.presentation.charities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.omise.android.tamboon.R
import com.omise.tamboon.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_charities.*

class CharitiesActivity : BaseActivity() {

    override val contentLayoutResourceId = R.layout.activity_charities
    override val toolbarTitle: String by lazy { getString(R.string.charities_title) }
    val viewModel by lazy { ViewModelProviders.of(this).get(CharitiesViewModel::class.java) }
    val adapter: CharitiesAdapter by lazy { CharitiesAdapter() }

    override fun setUp() {
        super.setUp()
        setupRecyclerView()
        viewModel.getCharities().observe(this, Observer {
            it?.let { adapter.charities = it }
        })
    }

    private fun setupRecyclerView() {
        rvCharities.apply {
            val linearLayoutManager = LinearLayoutManager(this@CharitiesActivity)
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
            adapter = this@CharitiesActivity.adapter
        }
        adapter.itemOnClickListener = { id -> navigateToCharityDonate(id) }
    }

    private fun navigateToCharityDonate(id: String) {

    }

}
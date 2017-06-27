package com.omise.tamboon.presentation

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.omise.android.tamboon.R
import kotlinx.android.synthetic.main.activity_charities.*

class CharitiesActivity : BaseActivity() {

    override val contentLayoutResourceId = R.layout.activity_charities
    override val toolbarTitle by lazy { getString(R.string.charities_title)?.let { it } ?: "" }

    override fun setUp() {
        super.setUp()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        rvCharities.apply {
            val linearLayoutManager = LinearLayoutManager(this@CharitiesActivity)
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
        }
    }
}
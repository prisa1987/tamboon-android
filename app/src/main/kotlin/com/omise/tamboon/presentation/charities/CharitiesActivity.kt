package com.omise.tamboon.presentation.charities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.omise.android.tamboon.R
import com.omise.tamboon.presentation.BaseActivity
import com.omise.tamboon.presentation.donation.CreateDonationAcitivity
import kotlinx.android.synthetic.main.activity_charities.*

class CharitiesActivity : BaseActivity() {

    override val contentLayoutResourceId = R.layout.activity_charities
    override val toolbarTitle: String by lazy { getString(R.string.charities_title) }
    val viewModel by lazy { ViewModelProviders.of(this).get(CharitiesViewModel::class.java) }
    val adapter: CharitiesAdapter by lazy { CharitiesAdapter() }

    override fun setUp() {
        super.setUp()
        viewModel.viewAction = this
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
        adapter.itemOnClickListener = { navigateToCharityDonate(it.first, it.second) }
    }

    private fun navigateToCharityDonate(id: String, name: String) {
        val intent = Intent(this, CreateDonationAcitivity::class.java).apply {
            putExtra(CreateDonationAcitivity.ARG_CHARITY_ID, id)
            putExtra(CreateDonationAcitivity.ARG_CHARITY_NAME, name)
        }
        startActivity(intent)
    }

}
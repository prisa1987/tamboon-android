package com.omise.tamboon.presentation.donation

import com.omise.android.tamboon.R
import com.omise.tamboon.presentation.BaseActivity

class CreateDonationAcitivity : BaseActivity() {

    companion object {
        val ARG_CHARITY_ID = "arg_charity_id"
    }

    override val contentLayoutResourceId: Int = R.layout.activity_create_donation
    override val toolbarTitle: String by lazy { getString(R.string.charities_title) }

}
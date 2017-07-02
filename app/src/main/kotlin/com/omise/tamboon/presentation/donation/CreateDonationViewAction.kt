package com.omise.tamboon.presentation.donation

import com.omise.tamboon.presentation.BaseViewAction

interface CreateDonationViewAction : BaseViewAction {

    fun alertDonateSucess(customerName: String, amount: String)

}
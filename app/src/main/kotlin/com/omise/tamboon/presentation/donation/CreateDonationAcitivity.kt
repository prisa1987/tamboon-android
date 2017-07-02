package com.omise.tamboon.presentation.donation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import com.omise.android.tamboon.R
import com.omise.tamboon.extension.DecimalDigitsInputFilter
import com.omise.tamboon.extension.untilDestory
import com.omise.tamboon.presentation.BaseActivity
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.view_amount.*
import kotlinx.android.synthetic.main.view_credit_card.*
import kotlinx.android.synthetic.main.view_pay_button.*
import java.util.regex.Pattern

class CreateDonationAcitivity : BaseActivity(), CreateDonationViewAction {

    companion object {
        val ARG_CHARITY_ID = "arg_charity_id"
        val ARG_CHARITY_NAME = "arg_charity_name"
    }

    private lateinit var charityId: String
    private lateinit var charityName: String
    private val viewModel: CreateDonationViewModel by lazy { ViewModelProviders.of(this).get(CreateDonationViewModel::class.java) }

    override val toolbarTitle: String by lazy { getString(R.string.charity_donation, charityName) }
    override val contentLayoutResourceId: Int = R.layout.activity_create_donation

    override fun handleIntent(intent: Intent) {
        super.handleIntent(intent)
        charityId = intent.getStringExtra(ARG_CHARITY_ID)
        charityName = intent.getStringExtra(ARG_CHARITY_NAME)
    }

    override fun setUp() {
        super.setUp()
        viewModel.viewAction = this
        etAmount.requestFocus()
        etAmount.filters = arrayOf(DecimalDigitsInputFilter(limitOfDigits = 8, limitOfDecimal = 2))

        viewModel.getUserInfo().observe(this, Observer {
            tvName.text = it?.card?.name
            tvCardNumber.text = "XXXX - XXXX - XXXX - ${it?.card?.lastDigits}"
            tvExpire.setText("${it?.card?.expireMonth?.toString()}/${it?.card?.expireYear.toString()}")
        })

        val etCVCObservable = PublishSubject.create<String>()
        val etAmountObservable = PublishSubject.create<String>()

        etCVC.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                etCVCObservable.onNext(s?.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        etAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                etAmountObservable.onNext(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        Observable.combineLatest(etAmountObservable, etCVCObservable, BiFunction<String, String, Boolean> { amount, cvc ->
            val isValidCVC = Pattern.compile("^[0-9]{3}").matcher(cvc).matches()
            val isValidAmount = if (amount.isNotBlank()) amount.toDouble() in 20..1000000 else false // chack in range (min and max)
            isValidAmount && isValidCVC
        }).subscribe { btPay.isEnabled = it }.untilDestory()


        btPay.setOnClickListener {
            viewModel.payAmount(charityId, etAmount.text.toString().toDouble())
        }
    }

    override fun alertDonateSucess(customerName: String, amount: String) {
        val alert = AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.successful_donation, charityName))
            setMessage(getString(R.string.donate_by, customerName, amount))
            setCancelable(true)
        }
        alert.setPositiveButton(getString(R.string.ok)) { dialog, id ->
            dialog.dismiss()
            finish()
        }
        alert.show()
    }
}


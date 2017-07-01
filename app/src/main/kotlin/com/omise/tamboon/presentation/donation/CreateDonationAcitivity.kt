package com.omise.tamboon.presentation.donation

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import com.omise.android.tamboon.R
import com.omise.tamboon.data.CreditInputFilter
import com.omise.tamboon.data.DecimalDigitsInputFilter
import com.omise.tamboon.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_create_donation.*

class CreateDonationAcitivity : BaseActivity() {

    companion object {
        val ARG_CHARITY_ID = "arg_charity_id"
    }

    override val contentLayoutResourceId: Int = R.layout.activity_create_donation
    override val toolbarTitle: String by lazy { getString(R.string.charity_donation) }

    override fun setUp() {
        super.setUp()
        etNumber.requestFocus()
        etNumber.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(10, 2))
        etCreditCard.filters = arrayOf<InputFilter>(CreditInputFilter(), InputFilter.LengthFilter(19))

        var isManualChange = false
        var oldText = ""
        etCreditCard.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                oldText = s.toString()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val isAdded = s.length > oldText.length
                if (isManualChange) {
                    isManualChange = false
                    return
                }

                if (isAdded) {
                    addNewDigit(s)
                } else {
                    removeLastDigit(s)
                }
                etCreditCard.setSelection(etCreditCard.text.length)
            }

            private fun addNewDigit(s: CharSequence) {
                val newPosition = s.filter { it != '-' }.lastIndex
                if (newPosition > 0 && newPosition % 4 == 0) {
                    isManualChange = true
                    val left = s.dropLast(1)
                    etCreditCard.setText("$left-${s.last()}")
                }
            }

            private fun removeLastDigit(s: CharSequence) {
                val lastPostion = oldText.filter { it != '-' }.lastIndex
                if (lastPostion > 0 && lastPostion % 4 == 0) {
                    isManualChange = true
                    val left = s.dropLast(1)
                    etCreditCard.setText(left)
                }
            }
        })
    }
}


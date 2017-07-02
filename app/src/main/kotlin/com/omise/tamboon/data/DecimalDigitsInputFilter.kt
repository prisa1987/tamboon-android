package com.omise.tamboon.data

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern

class DecimalDigitsInputFilter(limitOfDigits: Int, limitOfDecimal: Int) : InputFilter {

    private val pattern = Pattern.compile("[0-9]{0,${limitOfDigits - 1}}((\\.[0-9]{0,${limitOfDecimal - 1}})?)||(\\.)?")

    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        val matcher = pattern.matcher(dest)
        if (!matcher.matches()) {
            return ""
        }
        return null
    }

}
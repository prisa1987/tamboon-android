package com.omise.tamboon.data

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern

class CreditInputFilter : InputFilter {

    private val pattern = Pattern.compile("^[0-9]+((-[0-9]+){0,3})")


    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        val matcher = pattern.matcher(source)
        if(!source.contains("-")) {
            if (!matcher.matches()) {
                return ""
            }
        }
        return null
    }

}
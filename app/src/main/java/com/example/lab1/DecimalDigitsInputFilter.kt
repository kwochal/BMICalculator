package com.example.lab1

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern

class DecimalDigitsInputFilter(digitsBeforeDecimal: Int, digitsAfterDecimal: Int) : InputFilter {
    private val pattern: Pattern = Pattern.compile("([0-9]{0,$digitsBeforeDecimal}+)?(\\.[0-9]{0,$digitsAfterDecimal})?")
    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        val newText = dest.toString().substring(0, dstart) + source.toString().substring(start, end) + dest.toString().substring(dend)
        val matcher = pattern.matcher(newText)
        return if (!matcher.matches()) "" else null
    }
}
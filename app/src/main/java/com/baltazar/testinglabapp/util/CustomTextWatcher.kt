package com.baltazar.testinglabapp.util

import android.text.Editable
import android.text.TextWatcher

/**
 * CustomTextWatcher is to avoid using all the methods if they are not need it
 * @author Baltazar Rodriguez
 * @since v
 */
open class CustomTextWatcher: TextWatcher {
    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}
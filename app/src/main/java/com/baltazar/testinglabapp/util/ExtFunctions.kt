package com.baltazar.testinglabapp.util

import android.util.Patterns

/**
 * @author Baltazar Rodriguez
 * @since v
 */

fun String.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
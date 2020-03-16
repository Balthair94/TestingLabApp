package com.baltazar.testinglabapp.provider

import com.baltazar.testinglabapp.User

/**
 * @author Baltazar Rodriguez
 * @since v
 */
class ApiProvider {
    fun loginUser(email: String, password: String) = User(
        "Baltazar",
        "Rodriguez",
        25
    )
}
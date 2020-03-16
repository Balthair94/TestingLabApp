package com.baltazar.testinglabapp.provider

import com.baltazar.testinglabapp.User

/**
 * @author Baltazar Rodriguez
 * @since v
 */
class DataBaseProvider {

    private var mUser: User? = null

    fun getUser() = mUser

    fun insertUser(user: User) {
        mUser = user
    }
}
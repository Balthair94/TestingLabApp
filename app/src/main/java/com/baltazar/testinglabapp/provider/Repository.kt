package com.baltazar.testinglabapp.provider

import com.baltazar.testinglabapp.User

/**
 * @author Baltazar Rodriguez
 * @since v
 */
class Repository(
    private val mApi: ApiProvider,
    private val mDataBase: DataBaseProvider
) {

    fun userLogin(email: String, password: String): User =
        mApi.loginUser(email, password).also {
            mDataBase.insertUser(it)
        }

    fun isUserLogged(): Boolean {
        return mDataBase.getUser()?.firstName.isNullOrBlank().not()
    }
}
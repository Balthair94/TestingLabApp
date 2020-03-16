package com.baltazar.testinglabapp

/**
 * @author Baltazar Rodriguez
 * @since v
 */
interface MainContract {

    interface View {
        fun showUserName(user: User)

        fun showMessage(message: String)

        fun showLoadingIndicator()

        fun hideLoadingIndicator()
    }

    interface Presenter {
        fun init(view: View)

        fun validateUserSession()

        fun performLogin(email: String, password: String)
    }
}
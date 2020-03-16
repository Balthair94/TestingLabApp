package com.baltazar.testinglabapp

import android.os.Handler
import com.baltazar.testinglabapp.provider.Repository

/**
 * @author Baltazar Rodriguez
 * @since v
 */
class MainPresenter(private val mRepository: Repository) : MainContract.Presenter {

    private lateinit var mView: MainContract.View

    override fun init(view: MainContract.View) {
        mView = view
    }

    override fun performLogin(email: String, password: String) {
        mView.showLoadingIndicator()

        Handler().postDelayed({
            mView.showUserName(mRepository.userLogin(email, password))
            mView.hideLoadingIndicator()
        }, 3000)
    }

    override fun validateUserSession() {
        if (mRepository.isUserLogged()) {
            mView.showMessage("User logged")
        } else {
            mView.showMessage("User NOT logged")
        }
    }
}
package com.baltazar.testinglabapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.baltazar.testinglabapp.provider.ApiProvider
import com.baltazar.testinglabapp.provider.DataBaseProvider
import com.baltazar.testinglabapp.provider.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // I know, is not dependency injection
        mPresenter = MainPresenter(Repository(ApiProvider(), DataBaseProvider())).also { it.init(this) }
    }

    override fun onResume() {
        super.onResume()
        mPresenter.validateUserSession()

        button_login.setOnClickListener {
            mPresenter.performLogin(
                edit_text_email.text.toString(),
                edit_text_password.text.toString()
            )
        }
    }

    override fun showUserName(user: User) {
        Toast.makeText(this, "User ${user.firstName} logged", Toast.LENGTH_LONG).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoadingIndicator() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        progress_bar.visibility = View.GONE
    }
}

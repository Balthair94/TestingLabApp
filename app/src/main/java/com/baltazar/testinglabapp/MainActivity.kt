package com.baltazar.testinglabapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.baltazar.testinglabapp.provider.ApiProvider
import com.baltazar.testinglabapp.provider.DataBaseProvider
import com.baltazar.testinglabapp.provider.Repository
import com.baltazar.testinglabapp.util.isValidEmail
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var mPresenter: MainPresenter

    private val mIsDataValid: Boolean get() =
        edit_text_email.text.toString().isValidEmail() && edit_text_password.text.toString().isNotBlank()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = Repository(ApiProvider(), DataBaseProvider())
        mPresenter = MainPresenter(repo).also { it.init(this) }
    }

    override fun onResume() {
        super.onResume()
        mPresenter.validateUserSession()

        button_login.apply {
            isEnabled = mIsDataValid
            setOnClickListener {
                mPresenter.performLogin(
                    edit_text_email.text.toString(),
                    edit_text_password.text.toString()
                )
            }
        }

        edit_text_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                button_login.isEnabled = mIsDataValid
            }
        })

        edit_text_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                button_login.isEnabled = mIsDataValid
            }
        })
    }

    override fun showUserName(user: User) {
        Toast.makeText(this, "User ${user.firstName} logged", Toast.LENGTH_LONG).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoadingIndicator() {
        button_login.isEnabled = false
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        button_login.isEnabled = true
        progress_bar.visibility = View.GONE
    }
}

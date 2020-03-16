package com.baltazar.testinglabapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.baltazar.testinglabapp.provider.ApiProvider
import com.baltazar.testinglabapp.provider.DataBaseProvider
import com.baltazar.testinglabapp.provider.Repository
import com.baltazar.testinglabapp.util.CustomTextWatcher
import com.baltazar.testinglabapp.util.isValidEmail
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var mPresenter: MainPresenter

    private val mIsDataValid: Boolean
        get() =
            edit_text_email.text.toString().isValidEmail() && edit_text_password.text.toString()
                .isNotBlank()

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

        edit_text_email.addTextChangedListener(object : CustomTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
                button_login.isEnabled = mIsDataValid
            }
        })

        edit_text_password.addTextChangedListener(object : CustomTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
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
        hideKeyboard()
    }

    override fun hideLoadingIndicator() {
        button_login.isEnabled = true
        progress_bar.visibility = View.GONE
    }

    private fun hideKeyboard() {
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(edit_text_password.windowToken, 0);
    }
}

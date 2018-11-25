package ir.mymessage.view.activities

import android.content.Context
import android.content.Intent
import android.mymessage.R
import android.os.Bundle
import android.view.View
import android.widget.Toast
import ir.mymessage.presenter.LoginPresenter
import ir.mymessage.view.base.BaseActivity
import ir.mymessage.view.interfaces.LoginInterface
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginInterface {

    private var presenter: LoginPresenter? = null

    override fun getContentViewRes(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = LoginPresenter(this)
        setupLoginActivity()
    }

    override fun getUserName(): String {
        return et_username.text.toString().trim()
    }

    override fun getPassword(): String {
        return et_password.text.toString().trim()
    }

    override fun setupLoginActivity() {
        btn_login.setOnClickListener { onLoginClicked() }
        btn_login.setOnClickListener { startSignupActivity() }
    }

    override fun onLoginClicked() {
        presenter?.onLoginClicked()
    }

    override fun getContext(): Context {
        return this@LoginActivity
    }

    override fun startDialogsActivity() {
        startActivity(Intent(this@LoginActivity, DialogsActivity::class.java))
        finish()
    }

    override fun startSignupActivity() {
        startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
        finish()
    }

    override fun showUsernamePasswordError() {
        Toast.makeText(this, "نام کاربری یا کلمه عبور اشتباه است", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        rtl_progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        rtl_progress.visibility = View.GONE
    }
}

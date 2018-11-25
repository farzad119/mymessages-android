package ir.mymessage.view.activities

import android.content.Context
import android.content.Intent
import android.mymessage.R
import android.os.Bundle
import android.view.View
import android.widget.Toast
import ir.mymessage.presenter.SignupPresenter
import ir.mymessage.utils.MySharedPrefrences
import ir.mymessage.view.base.BaseActivity
import ir.mymessage.view.interfaces.SignupInterface
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : BaseActivity(), SignupInterface {

    private lateinit var presenter: SignupPresenter

    override fun getContentViewRes(): Int {
        return R.layout.activity_signup
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupMessagesActivity()
    }

    override fun setupMessagesActivity() {
        if (MySharedPrefrences(this@SignupActivity).isLoggedIn) {
            startDialogsActivity()
        } else {
            btn_signup.setOnClickListener {
                if (presenter.checkEmptyFields()) {
                    if (presenter.checkPassword()) {
                        onSignupClicked()
                    } else {
                        showPasswordMatchError()
                    }
                } else {
                    showEmptyFieldsError()
                }
            }
        }
        tv_login.setOnClickListener { startLoginActivity() }
    }

    override fun startDialogsActivity() {
        startActivity(Intent(this@SignupActivity, DialogsActivity::class.java))
        finish()
    }

    override fun startLoginActivity() {
        startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
        finish()
    }

    override fun getNickname(): String {
        return et_nickname.text.toString().trim()
    }

    override fun getUsername(): String = et_username.text.toString().trim()

    override fun getEmail(): String = et_email.text.toString().trim()

    override fun getPassword(): String = et_password.text.toString().trim()

    override fun getRepeatPassword(): String = et_repeat_password.text.toString().trim()

    override fun onSignupClicked() {
        presenter.signup()
    }

    override fun showProgress() {
        rtl_progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        rtl_progress.visibility = View.GONE
    }

    override fun showPasswordMatchError() {
        Toast.makeText(this, "پسورد های وارد شده یکسان نیستند", Toast.LENGTH_SHORT).show()
    }

    override fun showEmptyFieldsError() {
        Toast.makeText(this, "لطفا اطلاعات را وارد کنید", Toast.LENGTH_SHORT).show()
    }

    override fun getContext(): Context {
        return this
    }
}

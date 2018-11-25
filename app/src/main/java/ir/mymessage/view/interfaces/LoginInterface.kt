package ir.mymessage.view.interfaces

import android.content.Context

interface LoginInterface {
    fun getUserName(): String
    fun getPassword(): String
    fun getContext(): Context
    fun setupLoginActivity()
    fun onLoginClicked()
    fun startDialogsActivity()
    fun startSignupActivity()
    fun showUsernamePasswordError()
    fun showProgress()
    fun hideProgress()
}

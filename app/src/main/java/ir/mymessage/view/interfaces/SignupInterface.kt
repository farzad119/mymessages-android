package ir.mymessage.view.interfaces

import android.content.Context

import java.util.ArrayList

import ir.mymessage.model.remote.User
import ir.mymessage.model.response.FriendsResponse

interface SignupInterface {
    fun getNickname(): String
    fun getUsername(): String
    fun getEmail(): String
    fun getPassword(): String
    fun getRepeatPassword(): String
    fun getContext(): Context
    fun setupMessagesActivity()
    fun startDialogsActivity()
    fun startLoginActivity()
    fun onSignupClicked()
    fun showProgress()
    fun hideProgress()
    fun showPasswordMatchError()
    fun showEmptyFieldsError()
}

package ir.mymessage.presenter

import android.util.Log

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult

import ir.mymessage.model.response.LoginResponse
import ir.mymessage.model.remote.User
import ir.mymessage.utils.MySharedPrefrences
import ir.mymessage.view.base.BasePresenter
import ir.mymessage.view.interfaces.LoginInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(private val loginInterface: LoginInterface) : BasePresenter() {

    fun onLoginClicked() {
        val login = User()
        login.username = loginInterface.getUserName()
        login.password = loginInterface.getPassword()
        loginInterface.showProgress()
        apiService.login(login).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loginInterface.hideProgress()
                if (response.isSuccessful) {
                    if (response.body()?.status == true) {
                        val user = User()
                        user.userId = response.body()?.user?.userId
                        user.username = response.body()?.user?.username
                        user.nickname = response.body()?.user?.nickname

                        MySharedPrefrences(loginInterface.getContext()).saveUserInfo(user)
                        MySharedPrefrences(loginInterface.getContext()).login()
                        loginInterface.startDialogsActivity()
                    } else {
                        loginInterface.showUsernamePasswordError()
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.wtf("LoginPresenter", "onResponse Failure: " + t.toString())
            }
        })
    }
}

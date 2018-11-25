package ir.mymessage.presenter

import android.util.Log

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult

import ir.mymessage.model.remote.User
import ir.mymessage.model.response.LoginResponse
import ir.mymessage.utils.MySharedPrefrences
import ir.mymessage.view.base.BasePresenter
import ir.mymessage.view.interfaces.LoginInterface
import ir.mymessage.view.interfaces.SignupInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupPresenter(private val signupInterface: SignupInterface) : BasePresenter() {

    fun signup() {
        val user = User()
        user.nickname = signupInterface.getNickname()
        user.username = signupInterface.getUsername()
        user.password = signupInterface.getPassword()
        user.email = signupInterface.getEmail()
        signupInterface.showProgress()

        apiService.signup(user).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                signupInterface.hideProgress()
                if (response.isSuccessful) {
                    Log.i("SignupPresenter", "onResponse: suc")
                    if (response.body()?.status == true) {
                        Log.e("SignupPresenter", "onResponse: status true")
                        val user1 = User()
                        user1.userId = response.body()?.user?.userId
                        user1.username = response.body()?.user?.username
                        user1.nickname = response.body()?.user?.nickname
                        MySharedPrefrences(signupInterface.getContext()).saveUserInfo(user)
                        MySharedPrefrences(signupInterface.getContext()).login()
                        signupInterface.startDialogsActivity()
                    } else {
                        Log.e("SignupPresenter", "onResponse: status false")
                        signupInterface.showPasswordMatchError()
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.wtf("SignupPresenter", "onFailure: because :" + t.toString())
                signupInterface.hideProgress()
            }
        })
    }

    fun checkPassword(): Boolean {
        return signupInterface.getPassword() == signupInterface.getRepeatPassword()
    }

    fun checkEmptyFields(): Boolean {
        return !(signupInterface.getNickname().isEmpty()
                || signupInterface.getUsername().isEmpty()
                || signupInterface.getPassword().isEmpty())
    }
}

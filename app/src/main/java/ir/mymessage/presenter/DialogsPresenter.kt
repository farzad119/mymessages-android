package ir.mymessage.presenter

import android.util.Log

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult

import java.util.ArrayList

import ir.mymessage.model.local.DialogLocal
import ir.mymessage.model.local.MessageLocal
import ir.mymessage.model.local.UserLocal
import ir.mymessage.model.response.DialogsResponse
import ir.mymessage.utils.MySharedPrefrences
import ir.mymessage.view.base.BasePresenter
import ir.mymessage.view.interfaces.DialogsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DialogsPresenter(private val dialogsInterface: DialogsInterface) : BasePresenter() {
    private val avatar = "https://image.flaticon.com/icons/png/128/149/149071.png"

    fun getDialogs() {
        dialogsInterface.showProgress()
        apiService.dialogs(MySharedPrefrences(dialogsInterface.getContext()).userInfo.userId?:"")
                .enqueue(object : Callback<ArrayList<DialogsResponse>> {
                    override fun onResponse(call: Call<ArrayList<DialogsResponse>>, response: Response<ArrayList<DialogsResponse>>) {
                        dialogsInterface.hideProgress()
                        if (response.isSuccessful) {
                            val userArrayList = ArrayList<UserLocal>()
                            val dialogArrayList = ArrayList<DialogLocal>()
                            for (dialogsResponse in response.body().orEmpty()) {
                                Log.w("DialogsPresenter", dialogsResponse.friendId)
                                val userLocal = UserLocal(dialogsResponse.friendUserId,
                                        dialogsResponse.friendNickname,
                                        null,
                                        false,
                                        dialogsResponse.fcmToken)
                                userArrayList.add(userLocal)
                                val dialogLocal = DialogLocal(dialogsResponse.friendId,
                                        dialogsResponse.friendNickname,
                                        avatar,
                                        userArrayList,
                                        MessageLocal(dialogsResponse.friendUserId,
                                                userLocal,
                                                "Tap to see messages"),
                                        0)
                                dialogArrayList.add(dialogLocal)
                            }
                            dialogsInterface.displayDialogs(dialogArrayList)
                        } else {
                            Log.w("DialogPresenter", "onResponse unSuccess: ${response.raw()}")
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<DialogsResponse>>, t: Throwable) {
                        Log.w("DialogPresenter", "onFailure: $t")
                    }
                })
    }

    fun updateFcmToken() {
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {
                apiService.updateFcmToken(
                        MySharedPrefrences(dialogsInterface.getContext()).userInfo.userId?:"", task.result.token)
                        .enqueue(object : Callback<String> {
                            override fun onResponse(call: Call<String>, response: Response<String>) {
                                if (response.isSuccessful && response.body() == "true") {
                                    MySharedPrefrences(dialogsInterface.getContext()).saveFcmToken()
                                }
                            }

                            override fun onFailure(call: Call<String>, t: Throwable) {

                            }
                        })
                return@OnCompleteListener
            }
            Log.w("LoginActivity", "getInstanceId failed", task.exception)
        })
    }
}

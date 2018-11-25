package ir.mymessage.view.base

import android.content.Context

import ir.mymessage.service.ApiClient
import ir.mymessage.service.ApiInterface
import ir.mymessage.utils.MySharedPrefrences

abstract class BasePresenter {
    protected var context: Context? = null
    protected var apiService = ApiClient.getRetrofit()!!.create(ApiInterface::class.java)

}

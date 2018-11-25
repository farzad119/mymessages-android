package ir.mymessage.view.interfaces

import android.content.Context

import java.util.ArrayList

import ir.mymessage.model.local.DialogLocal

interface DialogsInterface {
    fun getContext(): Context
    fun setupDialogsActivity()
    fun startFriendsActivity()
    fun startMessagesActivity(dialogLocal: DialogLocal)
    fun displayDialogs(dialogArrayList: ArrayList<DialogLocal>)
    fun showProgress()
    fun hideProgress()
}

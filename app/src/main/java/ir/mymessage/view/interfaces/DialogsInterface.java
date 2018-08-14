package ir.mymessage.view.interfaces;

import android.content.Context;

import java.util.ArrayList;

import ir.mymessage.model.local.DialogLocal;

public interface DialogsInterface {
    void setupDialogsActivity();
    void startFriendsActivity();
    void startMessagesActivity(DialogLocal dialogLocal);
    void displayDialogs(ArrayList<DialogLocal> dialogArrayList);
    void showProgress();
    void hideProgress();
    Context getContext();
}

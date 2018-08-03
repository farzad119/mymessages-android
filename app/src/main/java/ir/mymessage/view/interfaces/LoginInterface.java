package ir.mymessage.view.interfaces;

import android.content.Context;

public interface LoginInterface {
    String getUserName();
    String getPassword();
    void setupLoginActivity();
    void onLoginClicked();
    Context getContext();
    void startDialogsActivity();
    void showUsernamePasswordError();
}

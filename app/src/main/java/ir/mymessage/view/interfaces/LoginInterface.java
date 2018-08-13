package ir.mymessage.view.interfaces;

import android.content.Context;

public interface LoginInterface {
    String getUserName();
    String getPassword();
    void setupLoginActivity();
    void onLoginClicked();
    void startDialogsActivity();
    void startSignupActivity();
    void showUsernamePasswordError();
    void showProgress();
    void hideProgress();
    Context getContext();
}

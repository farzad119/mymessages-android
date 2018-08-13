package ir.mymessage.view.interfaces;

import android.content.Context;

import java.util.ArrayList;

import ir.mymessage.model.remote.User;
import ir.mymessage.model.response.FriendsResponse;

public interface SignupInterface {
    void setupMessagesActivity();
    void startDialogsActivity();
    void startLoginActivity();
    String getNickname();
    String getUsername();
    String getEmail();
    String getPassword();
    String getRepeatPassword();
    void onSignupClicked();
    void showProgress();
    void hideProgress();
    void showPasswordMatchError();
    void showEmptyFieldsError();

    Context getContext();
}

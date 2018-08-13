package ir.mymessage.view.interfaces;

import android.content.Context;

import java.util.ArrayList;

import ir.mymessage.model.local.MessageLocal;
import ir.mymessage.model.remote.Friend;
import ir.mymessage.model.remote.User;
import ir.mymessage.model.response.FriendsResponse;

public interface FriendsInterface {
    void setupMessagesActivity();

    void displayAllFriends(ArrayList<FriendsResponse> friendArrayList);

    void clickOnSearch();

    void clickOnAddFriend(String userId, String friendUserId);

    void clickOnDeleteFriend(String userId, String friendId);

    void showDialogAddFriend(User user);

    void showProgress();

    void hideProgress();

    String getTextUsername();

    Context getContext();
}

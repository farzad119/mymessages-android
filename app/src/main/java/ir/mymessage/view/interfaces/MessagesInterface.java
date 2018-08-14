package ir.mymessage.view.interfaces;

import android.content.Context;

import java.util.ArrayList;

import ir.mymessage.model.local.MessageLocal;

public interface MessagesInterface {
    void addMessage(String content, String userId);
    void setupMessagesActivity();
    void displayMessages(ArrayList<MessageLocal> messageArrayList);
    void showSendingStatus();
    void hideSendingStatus();
    void showProgress();
    void hideProgress();
    Context getContext();
}

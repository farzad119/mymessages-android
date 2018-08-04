package ir.mymessage.view.interfaces;

import android.content.Context;

import java.util.ArrayList;

import ir.mymessage.model.local.MessageLocal;

public interface MessagesInterface {
    void setupMessagesActivity();
    void displayMessages(ArrayList<MessageLocal> messageArrayList);
    void visibleSendingStatus();
    void hideSendingStatus();
    Context getContext();
}

package ir.mymessage.presenter;

import java.util.ArrayList;

import ir.mymessage.model.local.MessageLocal;
import ir.mymessage.model.local.UserLocal;
import ir.mymessage.view.base.BasePresenter;
import ir.mymessage.view.interfaces.DialogsInterface;
import ir.mymessage.view.interfaces.MessagesInterface;

public class MessagesPresenter extends BasePresenter{

    private final MessagesInterface messagesInterface;

    public MessagesPresenter(MessagesInterface messagesInterface) {
        this.messagesInterface = messagesInterface;
    }

    public void getMessages(){

        ArrayList<MessageLocal> messageArrayList = new ArrayList<>();
        ArrayList<UserLocal> userArrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            UserLocal user;
            MessageLocal message;
            if (i<5) {
                user = new UserLocal("11", "Farzad", "https://image.flaticon.com/icons/png/128/149/149071.png", false);
                message = new MessageLocal(""+i, user, "سلام، خوبید؟");
            }else {
                user = new UserLocal("1", "Fatemh", "https://image.flaticon.com/icons/png/128/149/149071.png", false);
                message = new MessageLocal(""+i, user, "سلام");
            }

            userArrayList.add(user);
            messageArrayList.add(message);
        }

       messagesInterface.displayMessages(messageArrayList);

    }
}

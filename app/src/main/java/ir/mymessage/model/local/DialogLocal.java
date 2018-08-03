package ir.mymessage.model.local;

import com.stfalcon.chatkit.commons.models.IDialog;

import java.util.ArrayList;

/*
 * Created by troy379 on 04.04.17.
 */
public class DialogLocal implements IDialog<MessageLocal> {

    private String id;
    private String dialogPhoto;
    private String dialogName;
    private ArrayList<UserLocal> users;
    private MessageLocal lastMessage;

    private int unreadCount;

    public DialogLocal(String id, String name, String photo,
                       ArrayList<UserLocal> users, MessageLocal lastMessage, int unreadCount) {

        this.id = id;
        this.dialogName = name;
        this.dialogPhoto = photo;
        this.users = users;
        this.lastMessage = lastMessage;
        this.unreadCount = unreadCount;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDialogPhoto() {
        return dialogPhoto;
    }

    @Override
    public String getDialogName() {
        return dialogName;
    }

    @Override
    public ArrayList<UserLocal> getUsers() {
        return users;
    }

    @Override
    public MessageLocal getLastMessage() {
        return lastMessage;
    }

    @Override
    public void setLastMessage(MessageLocal lastMessage) {
        this.lastMessage = lastMessage;
    }

    @Override
    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }
}

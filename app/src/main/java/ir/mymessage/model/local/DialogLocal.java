package ir.mymessage.model.local;

import com.stfalcon.chatkit.commons.models.IDialog;

import java.util.ArrayList;

/*
 * Created by troy379 on 04.04.17.
 */
public class DialogLocal implements IDialog<MessageLocal> {
    private String dialogName;
    private String dialogPhoto;
    private String id;
    private MessageLocal lastMessage;
    private int unreadCount;
    private ArrayList<UserLocal> users;

    public DialogLocal(String id, String name, String photo, ArrayList<UserLocal> users, MessageLocal lastMessage, int unreadCount) {
        this.id = id;
        this.dialogName = name;
        this.dialogPhoto = photo;
        this.users = users;
        this.lastMessage = lastMessage;
        this.unreadCount = unreadCount;
    }

    public String getId() {
        return this.id;
    }

    public String getDialogPhoto() {
        return this.dialogPhoto;
    }

    public String getDialogName() {
        return this.dialogName;
    }

    public ArrayList<UserLocal> getUsers() {
        return this.users;
    }

    public MessageLocal getLastMessage() {
        return this.lastMessage;
    }

    public void setLastMessage(MessageLocal lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getUnreadCount() {
        return this.unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }
}
package ir.mymessage.model.local;

import com.stfalcon.chatkit.commons.models.IUser;

/*
 * Created by troy379 on 04.04.17.
 */
public class UserLocal implements IUser {
    private String avatar;
    private String fcmToken;
    private String id;
    private String name;
    private boolean online;

    public UserLocal(String id, String name, String avatar, boolean online, String fcmToken) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.online = online;
        this.fcmToken = fcmToken;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public boolean isOnline() {
        return this.online;
    }

    public String getFcmToken() {
        return this.fcmToken;
    }
}

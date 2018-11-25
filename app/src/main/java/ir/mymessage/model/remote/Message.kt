package ir.mymessage.model.remote

import com.google.gson.annotations.SerializedName

class Message {
    @SerializedName("content")
    var content: String? = null
    @SerializedName("fcm_token")
    var fcmToken: String? = null
    @SerializedName("friend_id")
    var friendId: String? = null
    @SerializedName("nickname")
    var nickname: String? = null
    @SerializedName("to_user_id")
    var toUserId: String? = null
    @SerializedName("user_id")
    var userId: String? = null
}

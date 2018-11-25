package ir.mymessage.model.response

import com.google.gson.annotations.SerializedName

class FriendsResponse {
    @SerializedName("created_at")
    var createdAt: String? = null
    @SerializedName("fcm_token")
    var fcmToken: String? = null
    @SerializedName("friend_id")
    var friendId: String? = null
    @SerializedName("friend_nickname")
    var friendNickname: String? = null
    @SerializedName("friend_user_id")
    var friendUserId: String? = null
    @SerializedName("nickname")
    var nickname: String? = null
    @SerializedName("updated_at")
    var updatedAt: String? = null
    @SerializedName("user_id")
    var userId: String? = null
}

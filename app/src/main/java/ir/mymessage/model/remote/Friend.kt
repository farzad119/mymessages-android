package ir.mymessage.model.remote

import com.google.gson.annotations.SerializedName

class Friend {
    @SerializedName("user_id")
    var userId: String? = null
    @SerializedName("nickname")
    var nickname: String? = null
    @SerializedName("friend_id")
    var friendId: String? = null
    @SerializedName("friend_nickname")
    var friendNickname: String? = null
    @SerializedName("friend_user_id")
    var friendUserId: String? = null
}

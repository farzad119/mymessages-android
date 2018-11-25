package ir.mymessage.model.response

import com.google.gson.annotations.SerializedName

class MessagesResponse {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("user_id")
    var userId: String? = null
    @SerializedName("nickname")
    var nickname: String? = null
    @SerializedName("to_friend_user_id")
    var toUserId: String? = null
    @SerializedName("content")
    var content: String? = null
    @SerializedName("sent_at")
    var sentAt: String? = null
    @SerializedName("updated_at")
    var updatedAt: String? = null
    @SerializedName("created_at")
    var createdAt: String? = null
}

package ir.mymessage.model.remote

import com.google.gson.annotations.SerializedName

class UpdateFcmToken {
    @SerializedName("user_id")
    var token: String? = null
    @SerializedName("user_id")
    var userId: String? = null
}
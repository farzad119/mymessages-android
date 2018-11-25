package ir.mymessage.model.remote

import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("nickname")
    var nickname: String? = null

    @SerializedName("password")
    var password: String? = null

    @SerializedName("profile_image")
    var profileImage: String? = null

    @SerializedName("user_id")
    var userId: String? = null

    @SerializedName("username")
    var username: String? = null

    @SerializedName("email")
    var email: String? = null
}

package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class Login(
        @SerializedName("username") var username: String?,
        @SerializedName("email") var email: String?,
        @SerializedName("password") var password: String?
)

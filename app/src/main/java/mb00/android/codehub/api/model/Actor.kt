package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class Actor(
        @SerializedName("login") var login: String?,
        @SerializedName("avatar_url") var avatarUrl: String?
)

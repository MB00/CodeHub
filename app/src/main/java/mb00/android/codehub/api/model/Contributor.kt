package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class Contributor(
        @SerializedName("login") var login: String?,
        @SerializedName("avatar_url") var avatarUrl: String?,
        @SerializedName("contributions") var contributions: String?
)

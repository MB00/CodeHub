package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


class User(
        @SerializedName("login") var login: String?,
        @SerializedName("avatar_url") var avatarUrl: String?,
        @SerializedName("name") var name: String?,
        @SerializedName("company") var company: String?,
        @SerializedName("location") var location: String?,
        @SerializedName("email") var email: String?,
        @SerializedName("blog") var website: String?,
        @SerializedName("created_at") var creationDate: String?
)

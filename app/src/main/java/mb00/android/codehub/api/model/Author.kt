package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class Author(
        @SerializedName("name") val name: String?,
        @SerializedName("date") val date: String?,
        @SerializedName("login") val login: String?,
        @SerializedName("avatar_url") val avatarUrl: String?
)
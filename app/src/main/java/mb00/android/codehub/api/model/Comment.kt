package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class Comment(
        @SerializedName("user") var user: User?,
        @SerializedName("created_at") var creationDate: String?,
        @SerializedName("body") var body: String?
)

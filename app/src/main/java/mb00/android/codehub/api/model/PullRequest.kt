package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class PullRequest(
        @SerializedName("state") var state: String?,
        @SerializedName("title") var title: String?,
        @SerializedName("number") var number: String?,
        @SerializedName("created_at") var creationDate: String?,
        @SerializedName("user") var user: User?
)

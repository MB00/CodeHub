package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class Issue(
        @SerializedName("state") var state: String?,
        @SerializedName("number") var number: String?,
        @SerializedName("title") var title: String?,
        @SerializedName("created_at") var openDate: String?,
        @SerializedName("closed_at") var closeDate: String?
)

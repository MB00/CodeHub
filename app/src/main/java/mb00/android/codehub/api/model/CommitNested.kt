package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class CommitNested(
        @SerializedName("author") var author: Author?,
        @SerializedName("message") var message: String?
)

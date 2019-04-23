package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class RepoResult(
        @SerializedName("items") var items: List<Repo>?,
        @SerializedName("total_count") var total_count: Int?
)

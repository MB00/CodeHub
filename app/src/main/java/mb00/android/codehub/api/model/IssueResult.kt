package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class IssueResult(
        @SerializedName("items") var items: List<Issue>?
)

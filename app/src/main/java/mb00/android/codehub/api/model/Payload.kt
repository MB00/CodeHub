package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class Payload(
        @SerializedName("issue") var issue: Issue?
)

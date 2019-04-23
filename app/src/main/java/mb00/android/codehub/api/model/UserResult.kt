package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class UserResult(
        @SerializedName("items") var items: List<User>?,
        @SerializedName("total_count") var total_count: Int?
)

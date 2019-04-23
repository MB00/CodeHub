package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class Readme(
        @SerializedName("name") var name: String?,
        @SerializedName("size") var size: String?,
        @SerializedName("content") var content: String?
)

package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class Code(
        @SerializedName("name") var name: String?,
        @SerializedName("path") var path: String?,
        @SerializedName("size") var size: String?,
        @SerializedName("type") var type: String?,
        @SerializedName("content") var content: String?
)

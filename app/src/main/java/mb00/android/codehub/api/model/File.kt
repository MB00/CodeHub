package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class File(
        @SerializedName("filename") var fileName: String?,
        @SerializedName("type") var type: String?,
        @SerializedName("language") var language: String?,
        @SerializedName("size") var size: String?,
        @SerializedName("content") var content: String?
)

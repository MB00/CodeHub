package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class GistFile(
        @SerializedName("filename") var fileName: String?,
        @SerializedName("size") var size: String?,
        @SerializedName("content") var content: String?
)

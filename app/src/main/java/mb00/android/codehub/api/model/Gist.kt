package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class Gist(
        @SerializedName("id") var id: String?,
        @SerializedName("files") var files: Map<String, GistFile>?,
        @SerializedName("description") var description: String?,
        @SerializedName("created_at") var creationDate: String?,
        @SerializedName("updated_at") var updateDate: String?,
        @SerializedName("comments") var commentCount: Int?,
        @SerializedName("owner") var owner: Owner?
)

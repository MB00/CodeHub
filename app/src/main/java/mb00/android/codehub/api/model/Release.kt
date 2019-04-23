package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class Release(
        @SerializedName("name") var name: String?,
        @SerializedName("published_at") var publicationDate: String?,
        @SerializedName("author") var author: Author?,
        @SerializedName("tarball_url") var tarballUrl: String?,
        @SerializedName("zipball_url") var zipballUrl: String?
)

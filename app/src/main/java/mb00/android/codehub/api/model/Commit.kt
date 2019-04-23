package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class Commit(
        @SerializedName("commit") var commitNested: CommitNested?,
        @SerializedName("author") var author: Author?
)

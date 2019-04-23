package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class Pulse(
        @SerializedName("type") var type: String?,
        @SerializedName("created_at") var creationDate: String?,
        @SerializedName("actor") var actor: Actor?,
        @SerializedName("repo") var repo: Repo?,
        @SerializedName("payload") var payload: Payload?
)

package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class Owner(
        @SerializedName("login") var login: String?
)

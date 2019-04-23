package mb00.android.codehub.api.model

import com.google.gson.annotations.SerializedName


data class Repo(
        @SerializedName("name") var name: String?,
        @SerializedName("full_name") var fullName: String?,
        @SerializedName("description") var description: String?,
        @SerializedName("language") var language: String?,
        @SerializedName("stargazers_count") var stargazersCount: String?,
        @SerializedName("forks_count") var forksCount: String?,
        @SerializedName("owner") var owner: Owner?
)

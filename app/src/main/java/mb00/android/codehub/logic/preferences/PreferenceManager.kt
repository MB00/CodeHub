package mb00.android.codehub.logic.preferences


interface PreferenceManager {

    var isSignedIn: Boolean

    var username: String

    var authHeader: String

    var accessToken: String

}

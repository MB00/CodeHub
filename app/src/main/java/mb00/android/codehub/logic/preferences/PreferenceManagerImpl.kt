package mb00.android.codehub.logic.preferences

import android.content.Context
import android.content.SharedPreferences

import mb00.android.codehub.data.PreferenceKeys


class PreferenceManagerImpl(context: Context) : PreferenceManager {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)

    override var isSignedIn: Boolean
        get() = sharedPreferences.getBoolean(PreferenceKeys.SIGNED_IN, false)
        set(isSignedIn) = sharedPreferences.edit().putBoolean(PreferenceKeys.SIGNED_IN, isSignedIn).apply()

    override var username: String
        get() = sharedPreferences.getString(PreferenceKeys.USER_NAME, "")
        set(username) = sharedPreferences.edit().putString(PreferenceKeys.USER_NAME, username).apply()

    override var authHeader: String
        get() = sharedPreferences.getString(PreferenceKeys.AUTH_HEADER, "")
        set(authHeader) = sharedPreferences.edit().putString(PreferenceKeys.AUTH_HEADER, authHeader).apply()

    override var accessToken: String
        get() = sharedPreferences.getString(PreferenceKeys.ACCESS_TOKEN, "")
        set(accessToken) = sharedPreferences.edit().putString(PreferenceKeys.ACCESS_TOKEN, accessToken).apply()

}

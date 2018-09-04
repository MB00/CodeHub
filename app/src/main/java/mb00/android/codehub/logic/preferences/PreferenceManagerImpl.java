package mb00.android.codehub.logic.preferences;


import android.content.Context;
import android.content.SharedPreferences;

import mb00.android.codehub.data.PreferenceKeys;

public class PreferenceManagerImpl implements PreferenceManager {

    private SharedPreferences sharedPreferences;

    public PreferenceManagerImpl(final Context context) {
        sharedPreferences = context.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public boolean isSignedIn() {
        return sharedPreferences.getBoolean(PreferenceKeys.SIGNED_IN, false);
    }

    @Override
    public void setSignedIn(boolean isSignedIn) {
        sharedPreferences.edit().putBoolean(PreferenceKeys.SIGNED_IN, isSignedIn).apply();
    }

    @Override
    public String getUsername() {
        return sharedPreferences.getString(PreferenceKeys.USER_NAME, "");
    }

    @Override
    public void setUsername(String username) {
        sharedPreferences.edit().putString(PreferenceKeys.USER_NAME, username).apply();
    }

    @Override
    public String getAuthHeader() {
        return sharedPreferences.getString(PreferenceKeys.AUTH_HEADER, "");
    }

    @Override
    public void setAuthHeader(String authHeader) {
        sharedPreferences.edit().putString(PreferenceKeys.AUTH_HEADER, authHeader).apply();
    }


}

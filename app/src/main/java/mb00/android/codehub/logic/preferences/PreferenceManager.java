package mb00.android.codehub.logic.preferences;


public interface PreferenceManager {

    boolean isSignedIn();
    void setSignedIn(boolean isSignedIn);

    String getUsername();
    void setUsername(String username);

    String getAuthHeader();
    void setAuthHeader(String authHeader);

}

package mb00.android.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Owner {

    @SerializedName("login")
    private String login;

    public String getLogin() {
        return login;
    }

}

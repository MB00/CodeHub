package mb00.android.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Branch {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

}

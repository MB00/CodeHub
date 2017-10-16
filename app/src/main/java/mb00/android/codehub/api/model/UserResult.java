package mb00.android.codehub.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class UserResult {

    @SerializedName("items")
    @Expose
    private List<User> items = null;

    @SerializedName("total_count")
    @Expose
    private Integer total_count;

    public List<User> getItems() {
        return items;
    }

    public Integer getTotal_count() {
        return total_count;
    }

}

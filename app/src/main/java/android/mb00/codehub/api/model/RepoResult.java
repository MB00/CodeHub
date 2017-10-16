package android.mb00.codehub.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class RepoResult {

    @SerializedName("items")
    private List<Repo> items = null;
    @SerializedName("total_count")
    private Integer total_count;

    public List<Repo> getItems() {
        return items;
    }

    public Integer getTotal_count() {
        return total_count;
    }

}

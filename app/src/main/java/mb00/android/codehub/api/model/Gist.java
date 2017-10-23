package mb00.android.codehub.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;


public class Gist {

    @SerializedName("id")
    private String id;
    @SerializedName("files")
    private Map<String, GistFile> files;
    @SerializedName("description")
    private String description;
    @SerializedName("created_at")
    private String creationDate;
    @SerializedName("updated_at")
    private String updateDate;
    @SerializedName("comments")
    private int commentCount;
    @SerializedName("owner")
    private Owner owner;

    public String getId() {
        return id;
    }

    public Map<String, GistFile> getFiles() {
        return files;
    }

    public String getDescription() {
        return description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public Owner getOwner() {
        return owner;
    }

}

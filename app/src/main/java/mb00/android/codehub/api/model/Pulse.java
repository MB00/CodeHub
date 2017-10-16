package mb00.android.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Pulse {

    @SerializedName("type")
    private String type;
    @SerializedName("created_at")
    private String creationDate;
    @SerializedName("actor")
    private Actor actor;
    @SerializedName("repo")
    private Repo repo;
    @SerializedName("payload")
    private Payload payload;

    public String getType() {
        return type;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public Actor getActor() {
        return actor;
    }

    public Repo getRepo() {
        return repo;
    }

    public Payload getPayload() {
        return payload;
    }

}

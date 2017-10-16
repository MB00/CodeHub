package mb00.android.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class CommitNested {

    // Mapping for nested JSON when getRepoCommits() is called in GitHubService.java

    @SerializedName("author")
    private Author author;
    @SerializedName("message")
    private String message;

    public Author getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

}

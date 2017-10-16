package mb00.android.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Commit {

    @SerializedName("commit")
    private CommitNested commit;
    @SerializedName("author")
    private Author author;

    public CommitNested getCommitNested() {
        return commit;
    }

    public Author getAuthor() {
        return author;
    }

}

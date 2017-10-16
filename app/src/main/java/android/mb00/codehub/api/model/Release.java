package android.mb00.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Release {

    @SerializedName("name")
    private String name;
    @SerializedName("published_at")
    private String publicationDate;
    @SerializedName("author")
    private Author author;
    @SerializedName("tarball_url")
    private String tarballUrl;
    @SerializedName("zipball_url")
    private String zipballUrl;

    public String getName() {
        return name;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTarballUrl() {
        return tarballUrl;
    }

    public String getZipballUrl() {
        return zipballUrl;
    }

}

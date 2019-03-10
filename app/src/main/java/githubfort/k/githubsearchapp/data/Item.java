package githubfort.k.githubsearchapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("owner")
    @Expose
    private Owner owner;

    @SerializedName("stargazers_count")
    @Expose
    private Integer stargazers;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("html_url")
    @Expose
    private String linkToRepository;



    public Integer getStargazers() {
        return stargazers;
    }

    public void setStargazers(Integer stargazers) {
        this.stargazers = stargazers;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }



    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getLinkToRepository() {
        return linkToRepository;
    }

    public void setLinkToRepository(String linkToRepository) {
        this.linkToRepository = linkToRepository;
    }

}

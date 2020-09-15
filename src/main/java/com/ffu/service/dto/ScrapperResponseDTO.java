package com.ffu.service.dto;

public class ScrapperResponseDTO {

    private String profilUrl;

    private String username;

    private String followers;

    private String following;

    private String publications;

    public String getProfilUrl() {
        return profilUrl;
    }

    public void setProfilUrl(String profilUrl) {
        this.profilUrl = profilUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getPublications() {
        return publications;
    }

    public void setPublications(String publications) {
        this.publications = publications;
    }
}

package com.ffu.service.dto;

public class ScrapperResponseDTO {

    private String profilUrl;

    private String username;

    private String name;

    private Long followers;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFollowers() {
        return followers;
    }

    public void setFollowers(Long followers) {
        this.followers = followers;
    }
}

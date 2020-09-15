package com.ffu.service.dto;

import javax.validation.constraints.NotBlank;

public class ScrapperRequestDTO {
    @NotBlank
    private String socialNetwork;

    private String[] categories;

    private String country;

    private long minFollowers;

    public String getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getMinFollowers() {
        return minFollowers;
    }

    public void setMinFollowers(long minFollowers) {
        this.minFollowers = minFollowers;
    }
}

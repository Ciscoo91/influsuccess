package com.ffu.service.dto;

import javax.validation.constraints.NotBlank;

public class ScrapperRequestDTO {
    @NotBlank
    private String socialNetwork;

    private String[] categories;

    private String countries;

    private int age;

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

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getMinFollowers() {
        return minFollowers;
    }

    public void setMinFollowers(long minFollowers) {
        this.minFollowers = minFollowers;
    }
}

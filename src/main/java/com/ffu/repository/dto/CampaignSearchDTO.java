package com.ffu.repository.dto;

public class CampaignSearchDTO {

    private String title;
    private String userLogin;
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CampaignSearchDTO{" +
            "title='" + title + '\'' +
            ", userLogin='" + userLogin + '\'' +
            ", status='" + status + '\'' +
            '}';
    }
}

package com.ffu.service.dto;

import javax.validation.constraints.NotNull;

public class MailUserDTO {
    @NotNull
    private String userEmail;
    @NotNull
    private String userLogin;
    @NotNull
    private String langKey;
    @NotNull
    private String content;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MailUserDTO{" +
            "userEmail='" + userEmail + '\'' +
            ", userLogin='" + userLogin + '\'' +
            ", langKey='" + langKey + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}

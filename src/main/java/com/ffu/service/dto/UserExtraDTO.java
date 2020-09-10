package com.ffu.service.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class UserExtraDTO {

    private Long id;

    @NotBlank
    private String country;

    @NotBlank
    private LocalDate birthday;

    @NotBlank
    private Long phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}

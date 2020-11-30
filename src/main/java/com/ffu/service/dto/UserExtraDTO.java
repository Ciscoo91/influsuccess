package com.ffu.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;

public class UserExtraDTO {
    private Long id;

    @NotBlank
    private String country;

    @NotBlank
    private LocalDate birthday;

    @NotBlank
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

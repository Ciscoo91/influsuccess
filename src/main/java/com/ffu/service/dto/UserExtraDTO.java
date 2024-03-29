package com.ffu.service.dto;

import com.ffu.domain.Country;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class UserExtraDTO {
    private Long id;

    private Country country;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
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

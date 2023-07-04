package com.ffu.service.mapper;


import com.ffu.domain.Country;
import com.ffu.service.dto.CountryDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CountryMapper {


    CountryDTO toDto(Country country);
    Country toEntity (CountryDTO countryDTO);

    public default Country countryFromId(String id) {
        if (id == null) {
            return null;
        }
        Country country = new Country();
        country.setCode(id);
        return country;
    }


}

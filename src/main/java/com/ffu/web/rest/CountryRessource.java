package com.ffu.web.rest;

import com.ffu.domain.Country;
import com.ffu.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing {@link com.ffu.domain.Country}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CountryRessource {
    private final Logger log = LoggerFactory.getLogger(CountryRessource.class);

    private final CountryRepository countryRepository;

    public CountryRessource(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    /**
     * {@code GET  /countries} : get all the countries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of campaigns in body.
     */
    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries() {
        log.debug("REST request to get all countries");
        List<Country> result = countryRepository.findAll();
        return new ResponseEntity(result, HttpStatus.OK);
    }
}

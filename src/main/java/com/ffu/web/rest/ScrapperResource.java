package com.ffu.web.rest;

import com.ffu.service.ScrapperService;
import com.ffu.service.dto.ScrapperRequestDTO;
import com.ffu.service.dto.ScrapperResponseDTO;
import com.sun.mail.iap.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.HashSet;

/**
 * REST controller for using scrapper
 */
@RestController
@RequestMapping("/api/scrapper")
public class ScrapperResource {

    private final ScrapperService scrapperService;

    public ScrapperResource(ScrapperService scrapperService) {
        this.scrapperService = scrapperService;
    }

    /**
     * {@code POST  scrapper/insta} : Scrapping according to scrapper request
     * @param scrapperRequestDTO
     */
    @PostMapping("/insta")
    public ResponseEntity<HashSet<ScrapperResponseDTO>> scrape(@Valid @RequestBody ScrapperRequestDTO scrapperRequestDTO) {

        HashSet<ScrapperResponseDTO> scrapperResponseDTO = scrapperService.scrape(scrapperRequestDTO);

        return ResponseEntity.ok(scrapperResponseDTO);
    }
}

package com.ffu.web.rest;

import com.ffu.service.ScrapperService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     */
    @PostMapping("/scrape")
    public void scrape() {
        scrapperService.scrape();
    }
}

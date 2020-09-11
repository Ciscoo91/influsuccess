package com.ffu.service;


import com.ffu.service.dto.ScrapperRequestDTO;
import com.ffu.service.dto.ScrapperResponseDTO;

public interface ScrapperService {

    /**
     * Scrape according to scrapper request
     * @param scrapperRequestDTO
     * @return
     */
    ScrapperResponseDTO scrape(ScrapperRequestDTO scrapperRequestDTO);
}

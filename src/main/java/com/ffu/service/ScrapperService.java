package com.ffu.service;


import com.ffu.service.dto.ScrapperRequestDTO;
import com.ffu.service.dto.ScrapperResponseDTO;

import java.util.HashSet;

public interface ScrapperService {

    /**
     * Scrape according to scrapper request
     * @param scrapperRequestDTO
     * @return
     */
    HashSet<ScrapperResponseDTO> scrape(ScrapperRequestDTO scrapperRequestDTO);
}

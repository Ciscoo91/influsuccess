package com.ffu.service.Impl;

import com.ffu.service.Scrapper.Scrapper;
import com.ffu.service.dto.ScrapperRequestDTO;
import com.ffu.service.dto.ScrapperResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class InstaScrapper  extends Scrapper {
    @Override
    public ScrapperResponseDTO scrape(ScrapperRequestDTO scrapperRequestDTO) {
        return null;
    }
}

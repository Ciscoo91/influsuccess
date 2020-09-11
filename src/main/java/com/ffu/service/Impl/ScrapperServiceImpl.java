package com.ffu.service.Impl;

import com.ffu.service.ScrapperService;
import com.ffu.service.dto.ScrapperRequestDTO;
import com.ffu.service.dto.ScrapperResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ScrapperServiceImpl implements ScrapperService {

    private final InstaScrapper instaScrapper;

    public ScrapperServiceImpl(InstaScrapper instaScrapper) {
        this.instaScrapper = instaScrapper;
    }

    /**{@inheritDoc}*/
    @Override
    public ScrapperResponseDTO scrape(ScrapperRequestDTO scrapperRequestDTO){
        switch (scrapperRequestDTO.getSocialNetwork()){
            case "insta":
                return instaScrapper.scrape(scrapperRequestDTO);
            default:
                return new ScrapperResponseDTO();
        }
    };
}

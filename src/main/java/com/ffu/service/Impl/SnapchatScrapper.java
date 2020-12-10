package com.ffu.service.Impl;

import com.ffu.service.dto.ScrapperRequestDTO;

import org.springframework.stereotype.Component;

@Component
public class SnapchatScrapper extends com.ffu.service.Scrapper.AbstractScrapper {


    @Override
    public void scrape(ScrapperRequestDTO scrapperRequestDTO) {
       this.searchAllSocialMediaProfilsByUrlPattern(scrapperRequestDTO, "(http|https):\\/\\/(www.)?snapchat.com\\/add\\/[a-z]+$");
    }


}

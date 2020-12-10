package com.ffu.service.Impl;

import com.ffu.domain.Influencer;
import com.ffu.service.dto.ScrapperRequestDTO;

import org.springframework.stereotype.Component;


import java.util.Set;



@Component
public class YoutubeScrapper extends com.ffu.service.Scrapper.AbstractScrapper {



    @Override
    public void scrape(ScrapperRequestDTO scrapperRequestDTO) {

       Set<Influencer> influencers = this.searchAllSocialMediaProfilsByUrlPattern(scrapperRequestDTO, "(http|https):\\/\\/(www.)?youtube.com\\/user\\/[a-z]+$");
       /* for (Influencer influencer : influencers) {
            this.useYoutubeApiToFillFollowersFollowingAndPublicationsFields(influencer);
        }*/
    }

    public void useYoutubeApiToFillFollowersFollowingAndPublicationsFields(Influencer influencer){
              
            }

}

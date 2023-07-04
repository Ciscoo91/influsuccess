package com.ffu.service.Impl;

import com.ffu.domain.Influencer;
import com.ffu.service.dto.ScrapperRequestDTO;

import org.springframework.stereotype.Component;


import java.util.Set;



@Component
public class TwitterScrappper extends com.ffu.service.Scrapper.AbstractScrapper {


    @Override
    public void scrape(ScrapperRequestDTO scrapperRequestDTO) {

       Set<Influencer> influencers = this.searchAllSocialMediaProfilsByUrlPattern(scrapperRequestDTO, "(http|https):\\/\\/(www.)?twitter.com\\/[a-z]+$");
       /* for (Influencer influencer : influencers) {
            this.useTwittereApiToFillFollowersFollowingAndPublicationsFields(influencer);
        }*/
    }

    public void useTwitterApiToFillFollowersFollowingAndPublicationsFields(Influencer influencer){
              
            }

}

package com.ffu.service;


import com.ffu.domain.CampaignCategory;
import com.ffu.domain.Country;
import com.ffu.domain.SocialNetwork;
import com.ffu.repository.CampaignCategoryRepository;
import com.ffu.repository.CountryRepository;
import com.ffu.repository.SocialNetworkRepository;
import com.ffu.service.Impl.InstaScrapper;
import com.ffu.service.Impl.SnapchatScrapper;
import com.ffu.service.Impl.TikTokScrapper;
import com.ffu.service.Impl.TwitterScrappper;
import com.ffu.service.Impl.YoutubeScrapper;
import com.ffu.service.dto.ScrapperRequestDTO;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapperService {


    private final CampaignCategoryRepository campaignCategoryRepository;
    private final CountryRepository countryRepository;
    private final SocialNetworkRepository socialNetworkRepository;
    private final InstaScrapper instaScrapper;
    private final TikTokScrapper tiktokScrapper;
    private final SnapchatScrapper snapchatScrapper;
    private final YoutubeScrapper youtubeScrapper;
    private final TwitterScrappper twitterScrappper;


    public ScrapperService(CampaignCategoryRepository campaignCategoryRepository, CountryRepository countryRepository, SocialNetworkRepository socialNetworkRepository, InstaScrapper instaScrapper, TikTokScrapper tiktokScrapper, SnapchatScrapper snapchatScrapper, YoutubeScrapper youtubeScrapper, TwitterScrappper twitterScrappper) {
        this.campaignCategoryRepository = campaignCategoryRepository;
        this.countryRepository = countryRepository;
        this.socialNetworkRepository = socialNetworkRepository;
        this.instaScrapper = instaScrapper;
        this.tiktokScrapper = tiktokScrapper;
        this.snapchatScrapper = snapchatScrapper;
        this.youtubeScrapper = youtubeScrapper;
        this.twitterScrappper = twitterScrappper;
    }

    public void scrape() {
        List<CampaignCategory> campaignCategories = campaignCategoryRepository.findAll();
        List<SocialNetwork> socialNetworks = socialNetworkRepository.findAll();
        List<Country> countries = countryRepository.findAll();


        campaignCategories.forEach(
            campaignCategory -> {
                ScrapperRequestDTO scrapperRequestDTO = new ScrapperRequestDTO();
                scrapperRequestDTO.setCategory(campaignCategory);
                countries.forEach(
                    country -> {
                        scrapperRequestDTO.setCountry(country);
                        socialNetworks.forEach(
                            socialNetwork -> {
                                scrapperRequestDTO.setSocialNetwork(socialNetwork);
                                switch (socialNetwork.getName()) {
                                    case Instagram:
                                        instaScrapper.scrape(scrapperRequestDTO);
                                        break;
                                    case Tiktok:
                                        tiktokScrapper.scrape(scrapperRequestDTO);
                                        break;
                                    case Snapchat:
                                        snapchatScrapper.scrape(scrapperRequestDTO);
                                        break;
                                    case Youtube:
                                        youtubeScrapper.scrape(scrapperRequestDTO);
                                        break;
                                    case Twitter:
                                        twitterScrappper.scrape(scrapperRequestDTO);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        );
                    }
                );
            });
    }
}

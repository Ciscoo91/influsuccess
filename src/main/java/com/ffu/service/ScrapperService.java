package com.ffu.service;


import com.ffu.domain.CampaignCategory;
import com.ffu.domain.Country;
import com.ffu.domain.SocialNetwork;
import com.ffu.repository.CampaignCategoryRepository;
import com.ffu.repository.CountryRepository;
import com.ffu.repository.SocialNetworkRepository;
import com.ffu.service.Impl.InstaScrapper;
import com.ffu.service.dto.ScrapperRequestDTO;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapperService {

    private final InstaScrapper instaScrapper;
    private final CampaignCategoryRepository campaignCategoryRepository;
    private final CountryRepository countryRepository;
    private final SocialNetworkRepository socialNetworkRepository;


    public ScrapperService(InstaScrapper instaScrapper, CampaignCategoryRepository campaignCategoryRepository, CountryRepository countryRepository, SocialNetworkRepository socialNetworkRepository) {
        this.instaScrapper = instaScrapper;
        this.campaignCategoryRepository = campaignCategoryRepository;
        this.countryRepository = countryRepository;
        this.socialNetworkRepository = socialNetworkRepository;
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

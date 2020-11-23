package com.ffu.service;

import com.ffu.repository.CampaignCategoryRepository;
import com.ffu.repository.CountryRepository;
import com.ffu.repository.SocialNetworkRepository;
import com.ffu.service.Impl.InstaScrapper;
import com.ffu.service.dto.ScrapperRequestDTO;
import com.ffu.service.dto.ScrapperResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class ScrapperService {

    private final InstaScrapper instaScrapper;
    private final CampaignCategoryRepository campaignCategoryRepository;
    private  final CountryRepository countryRepository;
    private final SocialNetworkRepository socialNetworkRepository;

    public ScrapperService(InstaScrapper instaScrapper, CampaignCategoryRepository campaignCategoryRepository, CountryRepository countryRepository, SocialNetworkRepository socialNetworkRepository) {
        this.instaScrapper = instaScrapper;
        this.campaignCategoryRepository = campaignCategoryRepository;
        this.countryRepository = countryRepository;
        this.socialNetworkRepository = socialNetworkRepository;
    }

    public void scrape() {
        campaignCategoryRepository.findAll().forEach(
            campaignCategory -> {
                ScrapperRequestDTO scrapperRequestDTO = new ScrapperRequestDTO();
                scrapperRequestDTO.setCategory(campaignCategory);
                countryRepository.findAll().forEach(
                    country -> {
                        scrapperRequestDTO.setCountry(country);
                        socialNetworkRepository.findAll().forEach(
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

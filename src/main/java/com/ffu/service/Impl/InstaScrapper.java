package com.ffu.service.Impl;


import com.ffu.domain.CampaignCategory;
import com.ffu.domain.Country;
import com.ffu.domain.SocialNetwork;
import com.ffu.service.dto.ScrapperRequestDTO;
import com.ffu.service.dto.ScrapperResponseDTO;
import com.ffu.web.rest.errors.ScrappingErrorException;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class InstaScrapper extends com.ffu.service.Scrapper.AbstractScrapper {

    @Override
    public void scrape(ScrapperRequestDTO scrapperRequestDTO) {

        // Store all  instagram profiles links corresponding to the category(ies)
        HashSet<String> profileUrls = new HashSet<>();

            for (int i = 0; i < 10; i += 10) {
                Set<Document> sitesToVisit = this.googleLinksScrapper(scrapperRequestDTO.getCategory().getName() +"+"+scrapperRequestDTO.getSocialNetwork() +"+"+ "influencers" +"+"+scrapperRequestDTO.getCountry().getName(), Integer.toString(i));
                sitesToVisit = sitesToVisit.stream().filter(document -> document != null).collect(Collectors.toSet());
                for (Document site : sitesToVisit) {
                    profileUrls.addAll(
                        site.select("a[href~=(?=^(http|https):\\/\\/(www.)?instagram.com\\/)(?=(?!.*\\/explorer\\/.*))(?=(?!.*\\/[p|d]\\/.*))]")
                            .stream()
                            .map(link -> link.attr("href").split("\\/\\?")[0])
                            .collect(Collectors.toSet())
                    );
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new ScrappingErrorException(e.getMessage());
                }
            }
         fillScrapperResponseDTODataFromProfil(profileUrls, scrapperRequestDTO.getCategory(), scrapperRequestDTO.getCountry(), scrapperRequestDTO.getSocialNetwork());
    }

    private void fillScrapperResponseDTODataFromProfil(HashSet<String> profileUrls, CampaignCategory category, Country country, SocialNetwork socialNetwork) {
        HashSet<ScrapperResponseDTO> scrapperResponseDTOS = new HashSet<>();
        for (String profileUrl : profileUrls) {
            Document instaProfil = this.getDocumentFromUrl(profileUrl, null);
            if (instaProfil != null) {
                String[] infos = instaProfil.select("meta[property=og:description]").attr("content").split(", ");
                if (infos.length == 3) {
                    String followers = infos[0].split("\\s")[0];
                    String following = infos[1].split("\\s")[0];
                    String publications = infos[2].split("\\s")[0];

                    ScrapperResponseDTO scrapperResponseDTO = new ScrapperResponseDTO();
                    scrapperResponseDTO.setCategory(category);
                    scrapperResponseDTO.setCountry(country);
                    scrapperResponseDTO.setSocialNetwork(socialNetwork);
                    scrapperResponseDTO.setProfilUrl(profileUrl);
                    scrapperResponseDTO.setFollowers(followers);
                    scrapperResponseDTO.setFollowing(following);
                    scrapperResponseDTO.setPublications(publications);
                    String[] linkSplit = profileUrl.split("/");
                    scrapperResponseDTO.setUsername(linkSplit[linkSplit.length - 1]);

                    scrapperResponseDTOS.add(scrapperResponseDTO);
                }
            }
        }
    }
}

package com.ffu.service.Impl;

import com.ffu.service.Scrapper.Scrapper;
import com.ffu.service.dto.ScrapperRequestDTO;
import com.ffu.service.dto.ScrapperResponseDTO;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InstaScrapper extends Scrapper {

    @Override
    public HashSet<ScrapperResponseDTO> scrape(ScrapperRequestDTO scrapperRequestDTO) {

        // Store all  instagram profiles links corresponding to the category(ies)
        HashSet<String> profileUrls = new HashSet<>();

        for (String category : scrapperRequestDTO.getCategories()) {
            for (int i = 0; i < 10; i += 10) {
                Set<Document> sitesToVisit = this.googleLinksScrapper(category + scrapperRequestDTO.getSocialNetwork() + "influencers" + scrapperRequestDTO.getCountry(), Integer.toString(i));
                sitesToVisit = sitesToVisit.stream().filter(document -> document != null).collect(Collectors.toSet());
                for (Document site : sitesToVisit) {
                    profileUrls.addAll(
                        site.select("a[href~=(?=^(http|https):\\/\\/(www.)?instagram.com\\/)(?=(?!.*\\/explorer\\/.*))(?=(?!.*\\/[p|d]\\/.*))]")
                            .stream()
                            .map(link -> link.attr("href"))
                            .collect(Collectors.toSet())
                    );
                }
                // TODO: 16/09/2020 for more results enable thread sleep before the request (to avoid  blocking)
                /*try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new ScrappingErrorException(e.getMessage());
                }*/
            }
        }

        return fillScrapperResponseDTODataFromProfil(profileUrls);
    }

    private HashSet<ScrapperResponseDTO> fillScrapperResponseDTODataFromProfil(HashSet<String> profileUrls) {
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
                    scrapperResponseDTO.setProfilUrl(profileUrl);
                    scrapperResponseDTO.setFollowers(followers);
                    scrapperResponseDTO.setFollowing(following);
                    scrapperResponseDTO.setPublications(publications);
                    String[] linkSplited = profileUrl.split("/");
                    scrapperResponseDTO.setUsername(linkSplited[linkSplited.length - 1]);

                    scrapperResponseDTOS.add(scrapperResponseDTO);

                    // TODO: 16/09/2020 launch and check the error on reading response
                    /* try {

                    JSONObject response = JsonReader.readJsonFromUrl(profileUrl+"?__a=1");
                    JSONObject user =  response.getJSONObject("graphql").getJSONObject("user");

                    ScrapperResponseDTO scrapperResponseDTO = new ScrapperResponseDTO();
                    scrapperResponseDTO.setProfilUrl(profileUrl);
                    scrapperResponseDTO.setEmail(user.get("business_email").toString());
                    scrapperResponseDTO.setFollowers(Long.valueOf( user.getJSONObject("edge_followed_by").get("count").toString()));
                    scrapperResponseDTO.setFollowing(Long.valueOf( user.getJSONObject("edge_follow").get("count").toString()));
                    scrapperResponseDTO.setUsername( user.get("full_name").toString());
                    scrapperResponseDTO.setPublications(Long.valueOf(user.getJSONObject("edge_owner_to_timeline_media").get("count").toString()));

                    scrapperResponseDTOS.add(scrapperResponseDTO);

                } catch (IOException e) {
                    throw new ScrappingErrorException(e.getMessage());
                }*/
                }
            }
        }
        return scrapperResponseDTOS;
    }


}

package com.ffu.service.Impl;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ffu.domain.Influencer;
import com.ffu.domain.SocialNetworkLink;
import com.ffu.domain.enumeration.SocialNetworkEnum;
import com.ffu.service.dto.ScrapperRequestDTO;
import io.undertow.util.BadRequestException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;

@Component
public class InstaScrapper extends com.ffu.service.Scrapper.AbstractScrapper {

    @Override
    public void scrape(ScrapperRequestDTO scrapperRequestDTO) {
        Set<Influencer> influencers = this.searchAllSocialMediaProfilsByUrlPattern(scrapperRequestDTO,
                "(http|https):\\/\\/(www.)?instagram.com\\/)(?=(?!.*\\/explore\\/.*))(?=(?!.*\\/[p|d]\\/.*)");
       /* for (Influencer influencer : influencers) {
            this.useInstaApiToFillFollowersFollowingAndPublicationsFields(influencer);
        }*/
    }

    public void useInstaApiToFillFollowersFollowingAndPublicationsFields(Influencer influencer) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://instagram40.p.rapidapi.com/proxy?url=https://www.instagram.com/"
                        + influencer.getUsername().replaceAll("\\s", "") + "/?__a=1"))
                .header("x-rapidapi-key", "6fb17c6f5dmshe617884df20af55p16fa7ejsn8b1df718899b")
                .header("x-rapidapi-host", "instagram40.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody()).build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonResponse = mapper.readTree(response.body());
                JsonNode jsonUser = jsonResponse.get("graphql").get("user");
                String email = null;
                if (jsonUser.has("business_email")) {
                    email = jsonUser.get("business_email").asText();
                }
                long followings = jsonUser.get("edge_follow").get("count").asLong();
                long followers = jsonUser.get("edge_followed_by").get("count").asLong();
                long publications = jsonUser.get("edge_owner_to_timeline_media").get("count").asLong();
                Long id = jsonUser.get("id").asLong();

                
                int i =0;
                long likeCount = 0;

                Iterator<Entry<String, JsonNode>> edges = jsonUser.get("edge_owner_to_timeline_media").get("edges")
                        .fields();
                        
                    while(edges.hasNext()){
                        i++;
                      likeCount+= edges.next().getValue().get("node").get("edge_liked_by").get("count").asLong();
                    }

                    BigDecimal rateEngagement = BigDecimal.valueOf(((likeCount/i)/publications)*100).setScale(2, RoundingMode.HALF_UP);
              
                    Optional<SocialNetworkLink> socialNetworkLinkOptional =
                            socialNetworkLinkRepository.findByInfluencer_idAndSocialNetwork_name(influencer.getId(), SocialNetworkEnum.Instagram);
                        if (socialNetworkLinkOptional.isPresent()) {
                            SocialNetworkLink socialNetworkLink = socialNetworkLinkOptional.get();
                            socialNetworkLink.setSocialNetworkUserId(id);
                            socialNetworkLink.setFollower(followers);
                            socialNetworkLink.setFollowing(followings);
                            socialNetworkLink.setPublication(publications);
                            socialNetworkLink.setRateEngagement(rateEngagement);
                            socialNetworkLinkRepository.save(socialNetworkLink);
                        }
                        influencer.setEmail(email);
                        influencerRepository.save(influencer);
                    } else {
                        throw new BadRequestException("influencer_username: " + influencer.getUsername() + "error" + response.statusCode() + "body" + response.body());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BadRequestException e) {
                    e.printStackTrace();
                }
    }
}

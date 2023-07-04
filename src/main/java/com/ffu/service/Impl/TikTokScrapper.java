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
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.Set;



@Component
public class TikTokScrapper extends com.ffu.service.Scrapper.AbstractScrapper {



    @Override
    public void scrape(ScrapperRequestDTO scrapperRequestDTO) {

       Set<Influencer> influencers = this.searchAllSocialMediaProfilsByUrlPattern(scrapperRequestDTO, "(http|https):\\/\\/(www.)?tiktok.com\\/@[a-z]+$");
       /* for (Influencer influencer : influencers) {
            this.useTikTokApiToFillFollowersFollowingAndPublicationsFields(influencer);
        }*/
    }

    public void useTikTokApiToFillFollowersFollowingAndPublicationsFields(Influencer influencer){
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://tiktok12.p.rapidapi.com/user-info?username="+influencer.getUsername()))
                    .header("x-rapidapi-key", "6fb17c6f5dmshe617884df20af55p16fa7ejsn8b1df718899b")
                    .header("x-rapidapi-host", "tiktok12.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
                try {
                    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                    if (response.statusCode() == 200) {
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode jsonResponse = mapper.readTree(response.body());
                        JsonNode jsonUser = jsonResponse.get("user");
                        String signature = jsonUser.get("signature").asText();

                        Long followings = jsonUser.get("stats").get("followingCount").asLong();
                        Long followers = jsonUser.get("stats").get("followerCount").asLong();
                        Long publications = jsonUser.get("stats").get("videoCount").asLong();
                        Long id = jsonUser.get("id").asLong();

                        Optional<SocialNetworkLink> socialNetworkLinkOptional =
                            socialNetworkLinkRepository.findByInfluencer_idAndSocialNetwork_name(influencer.getId(), SocialNetworkEnum.Tiktok.name());
                        if (socialNetworkLinkOptional.isPresent()) {
                            SocialNetworkLink socialNetworkLink = socialNetworkLinkOptional.get();
                            socialNetworkLink.setSocialNetworkUserId(id);
                            socialNetworkLink.setFollower(followers);
                            socialNetworkLink.setFollowing(followings);
                            socialNetworkLink.setPublication(publications);
                            socialNetworkLink.setSocialNetworkUserId(id);
                            socialNetworkLinkRepository.save(socialNetworkLink);
                        }
                       /* String[] tmp = signature.split(" ");
                            for (String string : tmp) {
                                if(string.contains("Snapchat") &&
                                !socialNetworkLinkRepository.findByInfluencer_idAndSocialNetwork_name(id, SocialNetworkEnum.Snapchat).isPresent()){
                                    SocialNetworkLink snapSocialNetworkLink = new SocialNetworkLink();
                                    snapSocialNetworkLink.setInfluencer(influencer);
                                    snapSocialNetworkLink.setFollower(0);
                                    snapSocialNetworkLink.setFollowing(0);
                                    snapSocialNetworkLink.setPublication(0);
                                    snapSocialNetworkLink.setLink("https://www.snapchat.com/add/" + string.split(":")[1]);
                                    snapSocialNetworkLink.setSocialNetwork(socialNetworkRepository.findByName(SocialNetworkEnum.Snapchat.name()).get());
                                   snapSocialNetworkLink = socialNetworkLinkRepository.saveAndFlush(snapSocialNetworkLink);
                                    influencer.addSocialNetworkLinks(snapSocialNetworkLink);
                                }

                            }*/
                            influencerRepository.save(influencer);
                        }
                    else {
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

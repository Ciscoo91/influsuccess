package com.ffu.service.Impl;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ffu.domain.Influencer;
import com.ffu.domain.SocialNetworkLink;
import com.ffu.domain.enumeration.SocialNetworkEnum;
import com.ffu.service.dto.ScrapperRequestDTO;
import com.ffu.service.dto.ScrapperResponseDTO;
import com.ffu.web.rest.errors.ScrappingErrorException;
import io.undertow.util.BadRequestException;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class InstaScrapper extends com.ffu.service.Scrapper.AbstractScrapper {



    @Override
    public void scrape(ScrapperRequestDTO scrapperRequestDTO) {

       // todo if(socialNetworkLinkRepository.findAllByNetwork_Name(SocialNetworkEnum.Instagram).size() <= 10000) {
            // Store all  instagram profiles links corresponding to the category(ies)
            HashSet<String> profileUrls = new HashSet<>();

            for (int i = 0; i < 10; i = i + 10) {
                Set<Document> sitesToVisit = this.googleLinksScrapper(
                    scrapperRequestDTO.getCategory().getName() + " "
                        + scrapperRequestDTO.getSocialNetwork().getName().toString() + " "
                        + "influencers" + " "
                        + scrapperRequestDTO.getCountry().getName(),
                    Integer.toString(i));
                sitesToVisit = sitesToVisit.stream().filter(document -> document != null).collect(Collectors.toSet());
                for (Document site : sitesToVisit) {
                    profileUrls.addAll(
                        site.select("a[href~=(?=^(http|https):\\/\\/(www.)?instagram.com\\/)(?=(?!.*\\/explore\\/.*))(?=(?!.*\\/[p|d]\\/.*))]")
                            .stream()
                            .map(link -> link.attr("href").split("\\/\\?")[0])
                            .collect(Collectors.toSet())
                    );
                }

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new ScrappingErrorException(e.getMessage());
                }
            }
            profileUrls.forEach(profileUrl -> {
                ScrapperResponseDTO scrapperResponseDTO = new ScrapperResponseDTO();
                scrapperResponseDTO.setCategory(scrapperRequestDTO.getCategory());
                scrapperResponseDTO.setCountry(scrapperRequestDTO.getCountry());
                scrapperResponseDTO.setSocialNetwork(scrapperRequestDTO.getSocialNetwork());
                scrapperResponseDTO.setProfilUrl(profileUrl);
                String[] linkSplit = profileUrl.split("/");
                scrapperResponseDTO.setUsername(linkSplit[linkSplit.length - 1]);
                this.saveInstaInfluencerBeforeUsingInstaApiToFillFollowersFollowingsAndPublicationsFields(scrapperResponseDTO);
            });
        //}
    }

    public void saveInstaInfluencerBeforeUsingInstaApiToFillFollowersFollowingsAndPublicationsFields(ScrapperResponseDTO scrapperResponseDTO){
        Optional<Influencer> influencerOptional = influencerRepository.findByUsername(scrapperResponseDTO.getUsername());
        if(influencerOptional.isPresent()) {
            Influencer influencer = influencerOptional.get();
            Optional<SocialNetworkLink> socialNetworkLinkOptional =
                socialNetworkLinkRepository.findByInfluencer_idAndSocialNetwork_name(influencerOptional.get().getId(), scrapperResponseDTO.getSocialNetwork().getName());
            if (socialNetworkLinkOptional.isPresent()) {
                influencer = influencerOptional.get();
                if (scrapperResponseDTO.getCategory() != null) {
                    influencer.getCategories().add(scrapperResponseDTO.getCategory());
                } else{
                    influencer.setCategories(Collections.singleton(scrapperResponseDTO.getCategory()));
                }

                if (scrapperResponseDTO.getCountry() != null) {
                    influencer.getCountries().add(scrapperResponseDTO.getCountry());
                } else{
                    influencer.setCountries(Collections.singleton(scrapperResponseDTO.getCountry()));
                }
            } else {
                SocialNetworkLink socialNetworkLink = new SocialNetworkLink();
                socialNetworkLink.setInfluencer(influencer);
                socialNetworkLink.setSocialNetwork(scrapperResponseDTO.getSocialNetwork());
                socialNetworkLink = socialNetworkLinkRepository.saveAndFlush(socialNetworkLink);
                influencer.getSocialNetworkLinks().add(socialNetworkLink);
            }
            influencerRepository.saveAndFlush(influencer);
          // this.useInstaApiToFillFollowersFollowingAndPublicationsFields(influencer);
        }
        else{
            Influencer influencer = new Influencer();
            influencer.setUsername(scrapperResponseDTO.getUsername());
            influencer.setFollowing("0");
            influencer.setFollowers("0");
            influencer.setPublications("0");

            influencer.setCategories(Collections.singleton(scrapperResponseDTO.getCategory()));
            influencer.setCountries(Collections.singleton(scrapperResponseDTO.getCountry()));

            influencer = influencerRepository.save(influencer);

            SocialNetworkLink socialNetworkLink = new SocialNetworkLink();
            socialNetworkLink.setInfluencer(influencer);
            socialNetworkLink.setSocialNetwork(scrapperResponseDTO.getSocialNetwork());
            socialNetworkLink.setLink(scrapperResponseDTO.getProfilUrl());
            socialNetworkLink = socialNetworkLinkRepository.saveAndFlush(socialNetworkLink);
            influencer.getSocialNetworkLinks().add(socialNetworkLink);
            influencerRepository.saveAndFlush(influencer);
            // this.useInstaApiToFillFollowersFollowingAndPublicationsFields(influencer);
        }

    }

    public void useInstaApiToFillFollowersFollowingAndPublicationsFields(Influencer influencer){

            if (influencer.getFollowers().equals("0") && influencer.getFollowing().equals("0")) {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://instagram40.p.rapidapi.com/proxy?url=https://www.instagram.com/"
                        + influencer.getUsername().replaceAll("\\s", "")
                        + "/?__a=1"))
                    .header("x-rapidapi-key", "6fb17c6f5dmshe617884df20af55p16fa7ejsn8b1df718899b")
                    .header("x-rapidapi-host", "instagram40.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
                try {
                    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                    if (response.statusCode() == 200) {
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode jsonResponse = mapper.readTree(response.body());
                        JsonNode jsonUser = jsonResponse.get("graphql").get("user");
                        String email = null;
                        if (jsonUser.has("business_email")) {
                            email = jsonUser.get("business_email").asText();
                        }
                        String following = jsonUser.get("edge_follow").get("count").asText();
                        String followers = jsonUser.get("edge_followed_by").get("count").asText();
                        String publications = jsonUser.get("edge_owner_to_timeline_media").get("count").asText();
                        Long id = jsonUser.get("id").asLong();

                        influencer.setEmail(email);
                        influencer.setFollowing(following);
                        influencer.setFollowers(followers);
                        influencer.setPublications(publications);
                        Optional<SocialNetworkLink> socialNetworkLinkOptional =
                            socialNetworkLinkRepository.findByInfluencer_idAndSocialNetwork_name(influencer.getId(), SocialNetworkEnum.Instagram);
                        if (socialNetworkLinkOptional.isPresent()) {
                            SocialNetworkLink socialNetworkLink = socialNetworkLinkOptional.get();
                            socialNetworkLink.setSocialNetworkUserId(id);
                            socialNetworkLinkRepository.save(socialNetworkLink);
                        }
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
}

package com.ffu.service.Scrapper;

import com.ffu.domain.Influencer;
import com.ffu.domain.SocialNetworkLink;
import com.ffu.repository.InfluencerRepository;
import com.ffu.repository.SocialNetworkLinkRepository;
import com.ffu.service.dto.ScrapperRequestDTO;
import com.ffu.service.dto.ScrapperResponseDTO;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractScrapper {

    private InfluencerRepository influencerRepository;

    private SocialNetworkLinkRepository socialNetworkLinkRepository;

    @Autowired
    public final void setInfluencerRepository(InfluencerRepository influencerRepository) {
        this.influencerRepository = influencerRepository;
    }

    @Autowired
    public final void setSocialNetworkLinkRepository(SocialNetworkLinkRepository socialNetworkLinkRepository) {
        this.socialNetworkLinkRepository = socialNetworkLinkRepository;
    }


    /**
     * Get document from an  url
     * @param url
     * @param requestParameters
     * @return
     */
    public Document getDocumentFromUrl(String url, String[] requestParameters) {
        try {
            if (requestParameters != null){
                return Jsoup.connect(url)
                    .userAgent("Mozilla")
                    .data(requestParameters)
                    .get();
            } else {
                Proxy proxy = new Proxy(Proxy.Type.HTTP,
                    new InetSocketAddress("110.44.122.214", 55443));
                return Jsoup.connect(url)
                    .userAgent("Mozilla")
                    .referrer("http://www.google.com")
                    .get();
            }
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Get links of  google's search
     * @param term
     * @param pageStart
     * @return
     */
    public Set<Document> googleLinksScrapper(String term, String pageStart){

        String[] requestParameters = {"q",term,"start",pageStart};
        Document document = this.getDocumentFromUrl("https://google.com/search", requestParameters);
        if(document != null) {
            Set<Document> sitesToVisit = document.select("a[href~=(?=(?i)^\\/url\\?q=)(?=(?!.*google.*))]")
                .stream().map(link -> {
                    String href = link.attr("href");
                    return href.substring(7, href.indexOf("&"));
                })
                .map(url -> this.getDocumentFromUrl(url, null))
                .collect(Collectors.toSet());
            return sitesToVisit;
        } else {
            return new HashSet<>();
        }
    }

    public void saveInfluencerFromScrapperResponse(ScrapperResponseDTO scrapperResponseDTO){

        if(StringUtils.isNotBlank(scrapperResponseDTO.getUsername())) {

            Influencer influencer = new Influencer();
            influencer.setUsername(scrapperResponseDTO.getUsername());
            if(StringUtils.isNotBlank(scrapperResponseDTO.getFollowers())){
                influencer.setFollowers(scrapperResponseDTO.getFollowers());
            }
            if(StringUtils.isNotBlank(scrapperResponseDTO.getFollowing())){
                influencer.setFollowing(scrapperResponseDTO.getFollowing());
            }
            if(StringUtils.isNotBlank(scrapperResponseDTO.getPublications())){
                influencer.setPublications(scrapperResponseDTO.getPublications());
            }
            if(StringUtils.isNotBlank(scrapperResponseDTO.getEmail())){
                influencer.setEmail(scrapperResponseDTO.getEmail());
            }

            Optional<Influencer> influencerOptional = influencerRepository.findByUsername(scrapperResponseDTO.getUsername());
            if(influencerOptional.isPresent()){
                influencer.setId(influencerOptional.get().getId());
                influencer.setCategories(influencerOptional.get().getCategories());
                influencer.setCountries(influencerOptional.get().getCountries());
                Optional<SocialNetworkLink> socialNetworkLinkOptional =
                    socialNetworkLinkRepository.findByInfluencer_idAndSocialNetwork_name(influencer.getId(), scrapperResponseDTO.getSocialNetwork().getName().toString());
                if(socialNetworkLinkOptional.isPresent()){
                    if(scrapperResponseDTO.getCategory() != null ){
                        influencer.getCategories().add(scrapperResponseDTO.getCategory());
                    }
                    if(scrapperResponseDTO.getCountry() != null){
                        influencer.getCountries().add(scrapperResponseDTO.getCountry());
                    }
                } else {
                    SocialNetworkLink socialNetworkLink = new SocialNetworkLink();
                    socialNetworkLink.setInfluencer(influencer);
                    socialNetworkLink.setSocialNetwork(scrapperResponseDTO.getSocialNetwork());
                    socialNetworkLink = socialNetworkLinkRepository.saveAndFlush(socialNetworkLink);
                    influencer.getSocialNetworkLinks().add(socialNetworkLink);
                }
            } else {
                influencer.setCategories(Collections.singleton(scrapperResponseDTO.getCategory()));
                influencer.setCountries(Collections.singleton(scrapperResponseDTO.getCountry()));

                influencer = influencerRepository.save(influencer);

                SocialNetworkLink socialNetworkLink = new SocialNetworkLink();
                socialNetworkLink.setInfluencer(influencer);
                socialNetworkLink.setSocialNetwork(scrapperResponseDTO.getSocialNetwork());
                socialNetworkLink.setLink(scrapperResponseDTO.getProfilUrl());
                socialNetworkLink = socialNetworkLinkRepository.saveAndFlush(socialNetworkLink);
                influencer.getSocialNetworkLinks().add(socialNetworkLink);

            }
            influencerRepository.saveAndFlush(influencer);
        }
    }


    public abstract void scrape(ScrapperRequestDTO scrapperRequestDTO);
}

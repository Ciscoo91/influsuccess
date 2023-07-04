package com.ffu.service.Scrapper;

import com.ffu.domain.Influencer;
import com.ffu.domain.SocialNetworkLink;
import com.ffu.repository.InfluencerRepository;
import com.ffu.repository.SocialNetworkLinkRepository;
import com.ffu.repository.SocialNetworkRepository;
import com.ffu.service.dto.ScrapperRequestDTO;
import com.ffu.service.dto.ScrapperResponseDTO;
import com.ffu.service.errors.ScrappingErrorException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractScrapper {

    protected InfluencerRepository influencerRepository;

    protected SocialNetworkLinkRepository socialNetworkLinkRepository;

    protected SocialNetworkRepository socialNetworkRepository;

    @Autowired
    public final void setInfluencerRepository(InfluencerRepository influencerRepository) {
        this.influencerRepository = influencerRepository;
    }

    @Autowired
    public final void setSocialNetworkLinkRepository(SocialNetworkLinkRepository socialNetworkLinkRepository) {
        this.socialNetworkLinkRepository = socialNetworkLinkRepository;
    }

    @Autowired
    public final void socialNetworkRepository(SocialNetworkRepository socialNetworkRepository) {
        this.socialNetworkRepository = socialNetworkRepository;
    }
    

    public abstract void scrape(ScrapperRequestDTO scrapperRequestDTO);

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
                    .referrer("http://www.google.com")
                    .data(requestParameters)
                    .get();
            } else {
                return Jsoup.connect(url)
                    .userAgent("Mozilla")
                    .referrer("http://www.google.com")
                    .get();
            }
        } catch (IOException e) {
            return null;
        }
        catch (IllegalArgumentException e){
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

    public Set<Influencer> searchAllSocialMediaProfilsByUrlPattern(ScrapperRequestDTO scrapperRequestDTO, String regularExpression){

        Set<Influencer> influencers = new HashSet<>();

        if(socialNetworkLinkRepository.findAllBySocialNetwork_Name(scrapperRequestDTO.getSocialNetwork().getName()).size() <= 10000) {
        // Store all  instagram profiles links corresponding to the category(ies)
        HashSet<String> profileUrls = new HashSet<>();

        for (int i = 0; i < 1; i++) {
            Set<Document> sitesToVisit = this.googleLinksScrapper(
                scrapperRequestDTO.getCategory().getName().toString() + " "
                    + scrapperRequestDTO.getSocialNetwork().getName().toString() + " "
                    + "influencers" + " "
                    + scrapperRequestDTO.getCountry().getName(),
                Integer.toString(i));
            sitesToVisit = sitesToVisit.stream().filter(document -> document != null).collect(Collectors.toSet());
            for (Document site : sitesToVisit) {
                profileUrls.addAll(
                    site.select("a[href~=(?=^"+regularExpression+")]")
                        .stream()
                        .map(link -> link.attr("href").split("\\/\\?")[0])
                        .collect(Collectors.toSet())
                );
            }

            try {
                Thread.sleep(5000);
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
           influencers.add(
               this.saveInfluencerBeforeUsingApiToFillFollowersFollowingsAndPublicationsFields(scrapperResponseDTO)
           );
        });
        }
        return influencers;
    }

    public Influencer saveInfluencerBeforeUsingApiToFillFollowersFollowingsAndPublicationsFields(ScrapperResponseDTO scrapperResponseDTO) {
        Optional<Influencer> influencerOptional = influencerRepository.findByUsername(scrapperResponseDTO.getUsername());
        if (influencerOptional.isPresent()) {
            Influencer influencer = influencerOptional.get();
            Optional<SocialNetworkLink> socialNetworkLinkOptional =
                socialNetworkLinkRepository.findByInfluencer_idAndSocialNetwork_name(influencerOptional.get().getId(), scrapperResponseDTO.getSocialNetwork().getName());
               
                influencer.addCategories(scrapperResponseDTO.getCategory());
                influencer.addCountries(scrapperResponseDTO.getCountry());

            if (!socialNetworkLinkOptional.isPresent()) {
                SocialNetworkLink socialNetworkLink = new SocialNetworkLink();
                socialNetworkLink.setInfluencer(influencer);
                socialNetworkLink.setSocialNetwork(scrapperResponseDTO.getSocialNetwork());
                socialNetworkLink.setLink(scrapperResponseDTO.getProfilUrl());
                socialNetworkLink.setFollower(Long.valueOf("0"));
                socialNetworkLink.setFollowing(Long.valueOf("0"));
                socialNetworkLink.setPublication(Long.valueOf("0"));
                socialNetworkLink = socialNetworkLinkRepository.saveAndFlush(socialNetworkLink);
                influencer.addSocialNetworkLinks(socialNetworkLink);
                }
            return influencerRepository.saveAndFlush(influencer);
        }
        else{
            Influencer influencer = new Influencer();
            influencer.setUsername(scrapperResponseDTO.getUsername());

            influencer.addCategories(scrapperResponseDTO.getCategory());
            influencer.addCountries(scrapperResponseDTO.getCountry());

            influencer = influencerRepository.save(influencer);

            SocialNetworkLink socialNetworkLink = new SocialNetworkLink();
            socialNetworkLink.setInfluencer(influencer);
            socialNetworkLink.setSocialNetwork(scrapperResponseDTO.getSocialNetwork());
            socialNetworkLink.setLink(scrapperResponseDTO.getProfilUrl());
            socialNetworkLink.setFollower(Long.valueOf("0"));
            socialNetworkLink.setFollowing(Long.valueOf("0"));
            socialNetworkLink.setPublication(Long.valueOf("0"));
            socialNetworkLink = socialNetworkLinkRepository.saveAndFlush(socialNetworkLink);
            influencer.addSocialNetworkLinks(socialNetworkLink);
            return influencerRepository.saveAndFlush(influencer);
        }
    }

}

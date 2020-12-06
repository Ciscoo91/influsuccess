package com.ffu.service.Scrapper;


import com.ffu.repository.InfluencerRepository;
import com.ffu.repository.SocialNetworkLinkRepository;
import com.ffu.service.dto.ScrapperRequestDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractScrapper {

    protected InfluencerRepository influencerRepository;

    protected SocialNetworkLinkRepository socialNetworkLinkRepository;

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

    public abstract void scrape(ScrapperRequestDTO scrapperRequestDTO);
}

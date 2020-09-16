package com.ffu.service.Scrapper;

import com.ffu.service.dto.ScrapperRequestDTO;
import com.ffu.service.dto.ScrapperResponseDTO;
import com.ffu.web.rest.errors.ScrappingErrorException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Scrapper {

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
                    .timeout(0)
                    .get();
            } else {
                return Jsoup.connect(url)
                    .userAgent("Mozilla")
                    .timeout(0)
                    .get();
            }
        } catch (IOException e) {
            if(e.getMessage().equals("HTTP error fetching URL")){
                return null;
            }
           else{
               throw new ScrappingErrorException(e.getMessage());
            }
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
        Set<Document> sitesToVisit =  document.select("a[href~=(?=(?i)^\\/url\\?q=)(?=(?!.*google.*))]")
            .stream().map(link -> {
                String href = link.attr("href");
                return href.substring(7, href.indexOf("&"));
            })
            .map(url -> this.getDocumentFromUrl(url, null))
            .collect(Collectors.toSet());
        return sitesToVisit;
    }

    public abstract HashSet<ScrapperResponseDTO> scrape(ScrapperRequestDTO scrapperRequestDTO);
}

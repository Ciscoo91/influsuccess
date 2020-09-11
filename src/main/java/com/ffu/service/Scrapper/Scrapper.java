package com.ffu.service.Scrapper;

import com.ffu.service.dto.ScrapperRequestDTO;
import com.ffu.service.dto.ScrapperResponseDTO;
import com.ffu.web.rest.errors.ScrappingErrorException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public abstract class Scrapper {

    /**
     * Get document from an  url
     * @param url
     * @param requestParameters
     * @return
     */
    public Document getDocumentFromUrl(String url, String[] requestParameters) {
        try {
            return Jsoup.connect(url)
                .data(requestParameters)
                .get();
        } catch (IOException e) {
            throw new ScrappingErrorException(e.getMessage());
        }
    }

    /**
     * Get links of  google's search
     * @param term
     * @param pageStart
     * @return
     */
    public Elements googleLinksScrapper(String term, String pageStart){

        String[] requestParameters = {"q",term,"start",pageStart};
        Document document = this.getDocumentFromUrl("https://google.com/search", requestParameters);
        return document.select("a[href]");
    }

    public abstract ScrapperResponseDTO scrape(ScrapperRequestDTO scrapperRequestDTO);
}

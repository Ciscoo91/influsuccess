package com.ffu.service.Impl;

import com.ffu.service.Scrapper.Scrapper;
import com.ffu.service.dto.ScrapperRequestDTO;
import com.ffu.service.dto.ScrapperResponseDTO;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class InstaScrapper extends Scrapper {


    private static final Pattern instaUrlPattern = Pattern.compile("^(http|https)://(www.)?instagram.com/");

    @Override
    public ScrapperResponseDTO scrape(ScrapperRequestDTO scrapperRequestDTO) {

        // Store all  instagram profiles links corresponding to the category(ies)
        HashSet<String> profileUrls = new HashSet<>();
        for (int i =0; i<2; i++) {
            Set<Document> sitesToVisit = this.googleLinksScrapper(" fitness instagram influencers", Integer.toString(i));

            for(Document site: sitesToVisit) {
                profileUrls.addAll(
                    site.select("a[href]")
                    .stream()
                    .map(link -> link.attr("href"))
                    .filter(link -> instaUrlPattern.matcher(link).find()
                        && !link.contains("explorer")
                        && !link.contains("/p/")
                        && !link.contains("/d/")
                    )
                    .collect(Collectors.toSet())
                );
            }
        }

        return fillScrapperResponseDTODataFromProfil(profileUrls);
    }

    private ScrapperResponseDTO fillScrapperResponseDTODataFromProfil(HashSet<String> profileUrls) {
        ScrapperResponseDTO scrapperResponseDTO = new ScrapperResponseDTO();

        return scrapperResponseDTO;
    }
}

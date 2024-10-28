package io.github.hellomaker.praise.service;

import io.github.hellomaker.praise.model.dto.QuoteLinkDTO;
import io.github.hellomaker.praise.spider.AbstractHTMLParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hellomaker
 */
public class QuoteLinkParser extends AbstractHTMLParser<List<QuoteLinkDTO>> {

    @Override
    public List<QuoteLinkDTO> parse(Document document) {
        List<QuoteLinkDTO> quoteLinkDTOS = new ArrayList<>();

        Elements items = document.select(".main3 .left .titletype .sright a");
        for (Element item : items) {
            QuoteLinkDTO quoteLinkDTO = new QuoteLinkDTO();
            quoteLinkDTO.setName(item.html());
            quoteLinkDTO.setHref(item.attribute("href").getValue());

            quoteLinkDTOS.add(quoteLinkDTO);
        }
        return quoteLinkDTOS;
    }

}

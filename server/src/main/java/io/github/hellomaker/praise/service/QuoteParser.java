package io.github.hellomaker.praise.service;

import io.github.hellomaker.praise.model.po.Quote;
import io.github.hellomaker.praise.spider.AbstractHTMLParser;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hellomaker
 */
public class QuoteParser extends AbstractHTMLParser<List<Quote>> {

    @Override
    public List<Quote> parse(Document document, String url) {
        Elements items = document.select(".main3 .left .sons .cont");
        Elements typeElement = document.select(".main3 .left .titletype .sright span");
        int appendType = 4;
        String appendValue = "";
        if (!typeElement.isEmpty()) {
            appendValue = typeElement.get(0).html();
            Element element = typeElement.get(0);
            Element parent = element.parent();
            if (parent != null && parent.parent() != null) {
                Element parent2 = parent.parent();
                Attribute id = parent2.attribute("id");
                try {
                    if (id != null && id.hasDeclaredValue()) {
                        String idValue = id.getValue();
                        if ("type1".equals(idValue)) {
                            appendType = 1;
                        } else if ("type2".equals(idValue)) {
                            appendType = 2;
                        } else if ("type3".equals(idValue)) {
                            appendType = 3;
                        }
                    } else {
                        System.err.println("not id!!");
                        System.out.println(parent);
                        System.out.println(parent2);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }

        List<Quote> quotes = new ArrayList<>();
        for (Element item : items) {
//            System.out.println(">>>> item:");
//            System.out.println(item);
            Elements subItems = item.select("a");
            if (subItems.size() < 2) {
                continue;
            }
            Element quoteContent = subItems.get(0);
            Element fromContent = subItems.get(1);

            Quote quote = new Quote();
            quote.setVerse(quoteContent.html());
            quote.setFrom(fromContent.html());
            quote.setQuoteHref(quoteContent.attribute("href").getValue());
            quote.setPoetryHref(fromContent.attribute("href").getValue());
            switch (appendType) {
                case 1:
                    quote.setQuoteType(appendValue);
                    break;
                case 2:
                    quote.setAuthor(appendValue);
                    break;
                case 3:
                    quote.setDynasty(appendValue);
                    break;
                default:
                    quote.setForm(appendValue);
                    break;
            }

            quotes.add(quote);
//            System.out.println("quote : " + quote.html());
//            System.out.println("form : " + from.html());
        }

        return quotes;
    }
}

package io.github.hellomaker.praise.spider;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class AbstractHTMLParser<T> extends AbstractParser<T>{

    public abstract T parse(Document document, String url);

    @Override
    public T parse(String html, String url) {
        Document document = Jsoup.parse(html);
        return parse(document, url);
    }
}

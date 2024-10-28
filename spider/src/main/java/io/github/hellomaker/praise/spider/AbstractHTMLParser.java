package io.github.hellomaker.praise.spider;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class AbstractHTMLParser<T> extends AbstractParser<T>{

    public abstract T parse(Document document);

    @Override
    public T parse(String html) {
        Document document = Jsoup.parse(html);
        return parse(document);
    }


}

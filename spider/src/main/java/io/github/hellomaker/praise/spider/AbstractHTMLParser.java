package io.github.hellomaker.praise.spider;



public class AbstractHTMLParser<T> extends AbstractParser<T>{


    @Override
    public T parse(String html) {
        Document document = Jsoup.parse(html);
        Elements items = document.select(".main3 .left .sons .cont");

        System.out.println(items);
        return null;
    }


}

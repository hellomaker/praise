package io.github.hellomaker.praise.spider;


import io.github.hellomaker.praise.spider.exception.NotFoundException;
import io.github.hellomaker.praise.spider.exception.OtherNotResourceException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author hellomaker
 */
public abstract class AbstractParser<T> implements Spider, Parser<T>{

    Spider spider = new DefaultSpider();

    public T parseFormHttp(String url, Object... params) throws OtherNotResourceException, NotFoundException {
        SpiderEntity spiderEntity = crawling(url, params);
        return parse(spiderEntity);
    }

    public T parse(SpiderEntity spiderEntity) {
        return parse(spiderEntity.getHtml(), spiderEntity.getUrl());
    }

    @Override
    public SpiderEntity crawling(String url, Object... params) throws OtherNotResourceException, NotFoundException {
        return spider.crawling(url, params);
    }

    @Override
    public void close() {
        spider.close();
    }
}

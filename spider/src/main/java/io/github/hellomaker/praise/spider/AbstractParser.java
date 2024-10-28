package io.github.hellomaker.praise.spider;


import io.github.hellomaker.praise.spider.exception.NotFoundException;
import io.github.hellomaker.praise.spider.exception.OtherNotResourceException;

/**
 * @author hellomaker
 */
public abstract class AbstractParser<T> implements Spider, Parser<T>{

    Spider spider = new DefaultSpider();

    public T parseFormHttp(String url, Object... params) throws OtherNotResourceException, NotFoundException {
        String html = crawling(url, params);
        return parse(html);
    }

    @Override
    public String crawling(String url, Object... params) throws OtherNotResourceException, NotFoundException {
        return spider.crawling(url, params);
    }

    @Override
    public void close() {
        spider.close();
    }
}

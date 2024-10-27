package io.github.hellomaker.praise.spider;


/**
 * @author hellomaker
 */
public abstract class AbstractParser<T> implements Spider, Parser<T>{

    Spider spider = new DefaultSpider();

    public T parseFormHttp(String url, Object... params) {
        String html = crawling(url, params);
        return parse(html);
    }

    @Override
    public String crawling(String url, Object... params) {
        return spider.crawling(url, params);
    }

    @Override
    public void close() {
        spider.close();
    }
}

package io.github.hellomaker.praise.spider;

import io.github.hellomaker.praise.spider.exception.NotFoundException;
import io.github.hellomaker.praise.spider.exception.OtherNotResourceException;

/**
 * @author xianzhikun
 *
 */
public interface Spider {

    String crawling(String url, Object... params) throws OtherNotResourceException, NotFoundException;

    void close();

}

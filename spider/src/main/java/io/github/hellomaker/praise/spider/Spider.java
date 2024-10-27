package io.github.hellomaker.praise.spider;

/**
 * @author xianzhikun
 *
 */
public interface Spider {

    String crawling(String url, Object... params);

    void close();

}

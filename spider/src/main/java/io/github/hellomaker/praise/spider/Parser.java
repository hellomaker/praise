package io.github.hellomaker.praise.spider;

/**
 * @author hellomaker
 */
public interface Parser<T> {

    T parse(String html);

}

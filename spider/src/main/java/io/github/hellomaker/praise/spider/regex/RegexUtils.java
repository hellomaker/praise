package io.github.hellomaker.praise.spider.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hellomaker
 */
public class RegexUtils {

    public static String parse(String url, Object... params) {
        String res = url;
        if (params != null) {
            for (int index = 0; index < params.length; index++) {
                res = res.replace("{" + (index + 1) + "}", String.valueOf(params[index]));
            }
        }
        return res;
    }


}

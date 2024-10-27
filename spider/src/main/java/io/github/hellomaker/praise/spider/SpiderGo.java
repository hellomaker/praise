package io.github.hellomaker.praise.spider;

/**
 * @author hellomaker
 */
public class SpiderGo {

    public static void go() {

//        Spider spider = new DefaultSpider();
//        String html = spider.crawling("https://www.gushiwen.cn/mingjus/default.aspx?page={1}&tstr=%e6%98%a5%e5%a4%a9&astr=&cstr=&xstr=", 1);
//
//        System.out.println(html);

        AbstractHTMLParser<Void> abstractHTMLParser = new AbstractHTMLParser<>();
        abstractHTMLParser.parseFormHttp("https://www.gushiwen.cn/mingjus/default.aspx?page={1}&tstr=%e6%98%a5%e5%a4%a9&astr=&cstr=&xstr=", 1);

    }


}

package io.github.hellomaker.praise.service;

import io.github.hellomaker.praise.mapper.QuoteDAO;
import io.github.hellomaker.praise.model.dto.QuoteLinkDTO;
import io.github.hellomaker.praise.model.po.Quote;
import io.github.hellomaker.praise.spider.exception.NotFoundException;
import io.github.hellomaker.praise.spider.exception.OtherNotResourceException;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hellomaker
 */
@Component
public class AppListener implements ApplicationRunner {

    @Resource
    QuoteDAO quoteDAO;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        QuoteLinkParser quoteLinkParser = new QuoteLinkParser();
        final String contextPath = "https://www.gushiwen.cn/";
        List<QuoteLinkDTO> quoteLinkDTOS = quoteLinkParser.parseFormHttp(contextPath + "/mingjus/default.aspx");

        QuoteParser quoteParser = new QuoteParser();
        for (QuoteLinkDTO quoteLinkDTO : quoteLinkDTOS) {
            int page = 1;
            while (true) {
                try {
                    List<Quote> quotes = quoteParser.parseFormHttp(contextPath + quoteLinkDTO.getHref() + "&page={1}", page);
                    if (quotes.isEmpty()) {
                        break;
                    }
                    if (quoteDAO.getCountOfQuote(quotes) > 0) {
                        break;
                    }
                    quoteDAO.saveQuoteList(quotes);
                    System.out.println("save : " + page + " , ok");
                    page++;
                } catch (OtherNotResourceException | NotFoundException e) {
                    break;
                }
            }
        }


    }
}

package io.github.hellomaker.praise.service;

import io.github.hellomaker.praise.mapper.QuoteDAO;
import io.github.hellomaker.praise.model.dto.QuoteLinkDTO;
import io.github.hellomaker.praise.model.po.Quote;
import io.github.hellomaker.praise.spider.exception.NotFoundException;
import io.github.hellomaker.praise.spider.exception.OtherNotResourceException;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collections;
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
        final String contextPath = "https://www.gushiwen.cn";
        List<QuoteLinkDTO> quoteLinkDTOS = quoteLinkParser.parseFormHttp(contextPath + "/mingjus/default.aspx");
        System.out.println(">>> all size : " + quoteLinkDTOS.size());
        QuoteParser quoteParser = new QuoteParser();
        for (int i = 0; i<quoteLinkDTOS.size(); i++) {
            System.out.println(">>>> start " + i);
            QuoteLinkDTO quoteLinkDTO = quoteLinkDTOS.get(i);
            int page = 1;
            while (true) {
                try {
                    List<Quote> quotes = quoteParser.parseFormHttp(contextPath + quoteLinkDTO.getHref() + "&page={1}", page);
                    if (quotes.isEmpty()) {
                        break;
                    }
                    List<Quote> insertQuote = new ArrayList<>();
                    for (Quote quote : quotes) {
                        if (quoteDAO.getCountOfQuote(Collections.singletonList(quote)) == 0) {
                            insertQuote.add(quote);
                        }
                    }
//                    if (quoteDAO.getCountOfQuote(quotes) > 0) {
//                        break;
//                    }
                    if (insertQuote.isEmpty()) {
                        break;
                    }
                    try {
                        quoteDAO.saveQuoteList(insertQuote);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("save : " + page + " , ok => " + quoteLinkDTO.getName() + " size => " + quotes.size() + " , link => " + quoteLinkDTO.getHref() + " , ok size => " + insertQuote.size());
                    page++;
                } catch (OtherNotResourceException | NotFoundException e) {
                    break;
                }
            }
        }
        System.out.println(">>>>> exit ...");

    }
}

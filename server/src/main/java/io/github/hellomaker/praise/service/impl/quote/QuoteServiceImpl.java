package io.github.hellomaker.praise.service.impl.quote;

import io.github.hellomaker.praise.common.BeanTransfer;
import io.github.hellomaker.praise.mapper.QuoteDAO;
import io.github.hellomaker.praise.model.po.Quote;
import io.github.hellomaker.praise.model.vo.QuoteVO;
import io.github.hellomaker.praise.service.quote.QuoteService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

/**
 * @author xianzhikun
 */
@Service
public class QuoteServiceImpl implements QuoteService {

    @Resource
    QuoteDAO quoteDAO;

    @Override
    public QuoteVO getRandomQuote() {
        Quote randomQuote = quoteDAO.getRandomQuote();
        if (randomQuote == null) {
            return null;
        }
        return BeanTransfer.transfer(randomQuote, QuoteVO.class, new BiConsumer<Quote, QuoteVO>() {
            @Override
            public void accept(Quote quote, QuoteVO quoteVO) {
                quoteVO.setId(null);
                quoteVO.setQuoteHref(null);
                quoteVO.setPoetryHref(null);
                if (quoteVO.getAuthor() != null) {
                    if (quoteVO.getQuoteType() == null) {
                        quoteVO.setQuoteType(quoteVO.getAuthor());
                    }
                    quoteVO.setAuthor(null);
                }
                if (quoteVO.getForm() != null) {
                    if (quoteVO.getQuoteType() == null) {
                        quoteVO.setQuoteType(quoteVO.getForm());
                    }
                    quoteVO.setForm(null);
                }
                if (quoteVO.getDynasty() != null) {
                    if (quoteVO.getQuoteType() == null) {
                        quoteVO.setQuoteType(quoteVO.getDynasty());
                    }
                    quoteVO.setDynasty(null);
                }

                String verse = quoteVO.getVerse();
                String from = "-" + quoteVO.getFrom();
                // 计算需要的最大宽度
                int maxWidth = Math.max(verse.length(), from.length());

                // 使用 String.format 进行右对齐
                String formattedLine1 = String.format("%" + maxWidth + "s", verse);
                String formattedLine2 = String.format("%" + maxWidth + "s", from);

                // 组合成一个字符串并返回
                String quoteFull = formattedLine1 + "\n" + formattedLine2;
                quoteVO.setQuote(quoteFull);
            }
        });
    }
}

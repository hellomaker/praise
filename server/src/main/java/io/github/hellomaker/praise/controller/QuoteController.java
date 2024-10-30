package io.github.hellomaker.praise.controller;

import io.github.hellomaker.praise.model.vo.QuoteVO;
import io.github.hellomaker.praise.mvc.view.R;
import io.github.hellomaker.praise.service.quote.QuoteService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xianzhikun
 */
@Controller
@RequestMapping("quote")
public class QuoteController {

    @Resource
    QuoteService quoteService;

    @GetMapping("gen")
    R<QuoteVO> gen() {
        QuoteVO randomQuote = quoteService.getRandomQuote();
        return R.ok(randomQuote);
    }

    @GetMapping("generate")
    R<Void> genSimple() {
        QuoteVO randomQuote = quoteService.getRandomQuote();
        return R.<Void>builder()
                .message("success")
                .code(2000)
                .append("quote", randomQuote.getQuote())
                .append("verse", randomQuote.getVerse())
                .append("from", randomQuote.getFrom())
                .append("type", randomQuote.getQuoteType())
                .buildPlain();
    }


}

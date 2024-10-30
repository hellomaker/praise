package io.github.hellomaker.praise.controller;

import io.github.hellomaker.praise.model.vo.QuoteVO;
import io.github.hellomaker.praise.mvc.view.R;
import io.github.hellomaker.praise.service.quote.QuoteService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


}

package io.github.hellomaker.praise.mapper;


import io.github.hellomaker.praise.model.po.Quote;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hellomaker
 */
@Repository
public interface QuoteDAO {

    int saveQuoteList(List<Quote> quotes);

    long getCountOfQuote(List<Quote> quotes);
}

package io.github.hellomaker.praise.model.po;

import lombok.Data;

/**
 * @author hellomaker
 */
@Data
public class Quote {

    private Integer id;

    private String quoteType;

    private String author;

    private String dynasty;

    private String form;

    private String verse;

    private String from;

    private String detailLink;

    private String poetryId;

    private String poetryHref;

    private String quoteHref;
}

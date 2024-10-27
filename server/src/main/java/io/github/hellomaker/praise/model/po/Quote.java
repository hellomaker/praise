package io.github.hellomaker.praise.model.po;

import lombok.Data;

/**
 * @author hellomaker
 */
@Data
public class Quote {

    Integer id;

    String type;

    String author;

    String dynasty;

    String form;

    String verse;

    String from;

    String detailLink;

    Integer poetryId;
}

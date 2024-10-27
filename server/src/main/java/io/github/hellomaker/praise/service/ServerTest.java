package io.github.hellomaker.praise.service;

import io.github.hellomaker.praise.spider.SpiderGo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class ServerTest {

    @PostConstruct
    void start() {
        SpiderGo.go();
    }


}

package org.saxing.a0045_drools_spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RuleServiceTest {

    @Autowired
    private RuleService ruleService;

    @Test
    void rule() {
        ruleService.rule();
    }
}
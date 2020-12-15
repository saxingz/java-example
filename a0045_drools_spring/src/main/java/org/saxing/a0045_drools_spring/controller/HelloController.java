package org.saxing.a0045_drools_spring.controller;

import org.saxing.a0045_drools_spring.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test controller
 *
 * @author saxing 2020/12/16 0:26
 */
@RestController
@RequestMapping("/hello")
public class HelloController {


    @Autowired
    private RuleService ruleService;

    @GetMapping("/test")
    public String test() {
        ruleService.rule();
        return "success";
    }

}

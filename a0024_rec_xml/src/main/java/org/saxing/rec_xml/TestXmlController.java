package org.saxing.rec_xml;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * testxml
 *
 * @author saxing 2019/2/26 15:15
 */
@RestController
public class TestXmlController {


    @PostMapping(value = "/tx", consumes = "application/xml")
    public String test(@RequestBody TestBean testBean){
        return testBean.getFromUserName() + " " + testBean.getToUserName();
    }

    @GetMapping(value = "/**")
    public String testAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        return "*";
    }

    @GetMapping(value = "/test2")
    public String test2(){
        return "hello test2";
    }

    @GetMapping(value = "/t*")
    public String t(){
        return "t*";
    }

}

package org.saxing.rec_xml;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/test2")
    public String test2(){
        return "hello test2";
    }

}

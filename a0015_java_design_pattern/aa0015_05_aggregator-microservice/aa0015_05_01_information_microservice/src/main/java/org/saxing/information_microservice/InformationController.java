package org.saxing.information_microservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * information controller
 *
 * @author saxing  2018/10/10 20:47
 */
public class InformationController {

    @RequestMapping(value = "/information", method = RequestMethod.GET)
    public String getPriceTitle(){
        return "The Product Title";
    }

}

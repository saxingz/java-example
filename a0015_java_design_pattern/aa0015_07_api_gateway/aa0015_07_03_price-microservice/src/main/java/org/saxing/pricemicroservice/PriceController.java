package org.saxing.pricemicroservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * price controller
 *
 * @author saxing  2018/10/23 21:40
 */
@RestController
public class PriceController {

    /**
     * get price
     *
     * @return
     */
    @RequestMapping(value = "/price", method = RequestMethod.GET)
    private String getPrice(){
        return "20";
    }

}

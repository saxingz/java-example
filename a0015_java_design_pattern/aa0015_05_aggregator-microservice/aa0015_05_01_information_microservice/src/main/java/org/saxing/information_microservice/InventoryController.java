package org.saxing.information_microservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Inventory Controller
 * 
 * @author saxing  2018/10/10 20:44
 */
@RestController
public class InventoryController {

    @RequestMapping(value = "/inventories", method = RequestMethod.GET)
    public int getProductInventories() {
        return 5;
    }
    
}

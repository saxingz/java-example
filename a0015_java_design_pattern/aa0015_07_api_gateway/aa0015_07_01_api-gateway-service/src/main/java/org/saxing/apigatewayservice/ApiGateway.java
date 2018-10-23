package org.saxing.apigatewayservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * api gateway
 *
 * @author saxing  2018/10/23 21:59
 */
@RestController
public class ApiGateway {

    @Resource
    private ImageClient imageClient;
    @Resource
    private PriceClient priceClient;

    @RequestMapping("/desktop")
    public DesktopProduct getDesktopProduct(){
        DesktopProduct desktopProduct = new DesktopProduct();
        desktopProduct.setImagePath(imageClient.getImagePath());
        desktopProduct.setPrice(priceClient.getPrice());
        return desktopProduct;
    }

    @RequestMapping("/mobile")
    public MobileProduct getMobileProduct(){
        MobileProduct mobileProduct = new MobileProduct();
        mobileProduct.setPrice(priceClient.getPrice());
        return mobileProduct;
    }

}

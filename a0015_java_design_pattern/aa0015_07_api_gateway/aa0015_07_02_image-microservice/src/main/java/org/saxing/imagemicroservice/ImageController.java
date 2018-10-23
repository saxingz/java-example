package org.saxing.imagemicroservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * image controller
 *
 * @author saxing  2018/10/23 21:42
 */
@RestController
public class ImageController {

    /**
     * image path
     *
     * @return
     */
    @RequestMapping(value = "/image-path", method = RequestMethod.GET)
    public String getImagePath(){
        return "/product-image.png";
    }

}

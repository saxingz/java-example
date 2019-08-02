package org.saxing.boot2;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class TestController {

    @GetMapping("/geta")
    public String getA(){
        String res = "a  " + new Random().nextFloat();
        System.out.println(res);
        return res;
    }

    @GetMapping("/getb")
    public String getB(){
        String res = "b  " + new Random().nextFloat();
        System.out.println(res);
        return res;
    }

}

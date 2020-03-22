package org.saxing.boot2;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * test
 *
 * @author saxing 2020/3/22 22:51
 *
 */
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

    @GetMapping("/getc")
    public String getC(){
        String res = "c  " + new Random().nextFloat();
        System.out.println(res);
        return res;
    }

    @GetMapping("/get3s")
    public String get3s(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ddd";
    }

}

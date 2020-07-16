package org.saxing.java8;

import org.apache.commons.lang3.StringUtils;

/**
 * STR TEST
 *
 * @author saxing 2020/7/8 23:41
 */
public class StrTest {

    public static void main(String[] args) {
        StringBuilder res = new StringBuilder();
        String path = "log\\2020-05-03\\6946.log";
        for (int i = path.length() - 1; i > 0; i--) {
            char c = path.charAt(i);
            // 数字0-9
            if (c >= 48 && c <= 57){
                res.append(c);
            }
            // 92=\   47=/
            if (c == 92 || c ==47){
                break;
            }
        }
        System.out.println(res.reverse().toString());
    }

    public static Long getLogId(String path){
        StringBuilder res = new StringBuilder();
        for (int i = path.length() - 1; i > 0; i--) {
            char c = path.charAt(i);
            if (c >= '0' && c <= '9'){
                res.append(c);
            }
            if (c == '/' || c == '\\'){
                break;
            }
        }
        String num = res.reverse().toString();
        if (StringUtils.isNumeric(num)){
            return Long.parseLong(num);
        }
        return 0L;
    }

}

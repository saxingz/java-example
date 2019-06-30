package org.saxing.a.algorithm2;

import java.util.ArrayList;
import java.util.List;

/**
 * leet code 22
 */
public class GenerateParentheses {



    public static void main(String[] args) {
        List<String> res1 = generateParenthesis1(3);
        System.out.println(res1);
    }


//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<>();
        gen1("", result, n, n);
        return result;
    }
    public static void gen1(String sub, List<String> result, int left, int right){
        if (left == 0 && right == 0){
            result.add(sub);
            return;
        }

        if (left > 0){
            gen1(sub + "(", result, left - 1, right);
        }
        if (right > left){
            gen1(sub + ")", result, left, right - 1);
        }
    }


}

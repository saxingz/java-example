package org.saxing.a.algorithm;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(2));
    }


    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateHelper("", result, n, n);
        return result;
    }

    public static void generateHelper(String sub, List<String> result, int left, int right){
        if (left == 0 && right == 0){
            result.add(sub);
            return;
        }

        if (left > 0){
            generateHelper(sub + "(", result, left - 1, right);
        }
        if (right > left){
            generateHelper(sub + ")", result, left, right - 1);
        }
    }

}

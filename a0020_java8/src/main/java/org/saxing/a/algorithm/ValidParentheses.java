package org.saxing.a.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

    public static void main(String[] args) {


        System.out.println(isValid("()[({})]()()()"));


    }

    public static boolean isValid(String s){
        Stack<String> stack = new Stack<>();
        Map<String, String> parents = new HashMap<>();
        parents.put(")", "(");
        parents.put("]", "[");
        parents.put("}", "{");

//        String[] ss = str.split("");
        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            if (!parents.keySet().contains(c)){
                stack.push(c);
            }else if (parents.keySet().contains(c)){
                String temp = stack.isEmpty() ? "#" : stack.pop();
                if (!temp.equals(parents.get(c))){
                    return false;
                }
            }
        }

        return stack.size() == 0;

    }

}

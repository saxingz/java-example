package org.saxing.a.algorithm2;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * leet code 20
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<String> stack = new Stack<>();
        Map<String, String> parents = new HashMap<>();
        parents.put(")", "(");
        parents.put("]", "[");
        parents.put("}", "{");


        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            if (!parents.keySet().contains(c)){
                stack.push(c);
            } else if (parents.keySet().contains(c)){
                String temp = stack.isEmpty() ? "#" : stack.pop();
                if (!temp.equals(c)){
                    return false;
                }
            }
        }

        return stack.size() == 0;
    }

}

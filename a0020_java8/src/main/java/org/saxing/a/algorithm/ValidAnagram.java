package org.saxing.a.algorithm;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    public static void main(String[] args) {
        String s = "fds";
        String t = "sdf";
        System.out.println(isAnagram(s, t));
    }

    public static boolean isAnagram(String s, String t) {
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMmap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            Character tempS = s.charAt(i);
            Integer num = sMap.get(tempS);
            if (num == null){
                sMap.put(tempS, 0);
            }else{
                sMap.put(tempS, num + 1);
            }
        }

        for (int i = 0; i < t.length(); i++) {
            Character tempS = t.charAt(i);
            Integer num = tMmap.get(tempS);
            if (num == null){
                tMmap.put(tempS, 0);
            }else{
                tMmap.put(tempS, num + 1);
            }
        }
        return sMap.equals(tMmap);

    }

}

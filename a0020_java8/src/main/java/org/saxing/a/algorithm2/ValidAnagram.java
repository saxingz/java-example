package org.saxing.a.algorithm2;

import java.util.*;

/**
 * leet code 242
 */
public class ValidAnagram {

    public static String getA1() {
        return "afdksjlklgfd";
    }
    public static String getA2() {
        return "afldkdsjlkgf";
    }
    public static String getB1() {
        return "afldkdsjlkgfffffffff";
    }

    public static void main(String[] args) {

        System.out.println(isAnagram1(getA1(), getA2()));
        System.out.println(isAnagram1(getA1(), getB1()));

        //-------------
        System.out.println(isAnagram2(getA1(), getA2()));
        System.out.println(isAnagram2(getA1(), getB1()));

    }


    /**
     * 方案2 计数
     */
    public static boolean isAnagram2(String s, String t) {
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Set<Character> characters = sMap.keySet();
            if (characters.contains(c)){
                sMap.put(c, sMap.get(c) + 1);
            }else{
                sMap.put(c, 0);
            }
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Set<Character> characters = tMap.keySet();
            if (characters.contains(c)){
                tMap.put(c, tMap.get(c) + 1);
            }else{
                tMap.put(c, 0);
            }
        }

        return sMap.equals(tMap);

    }

    /**
     * 方案一， 排序
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram1(String s, String t) {
        String[] as = s.split("");
        List<String> alist = Arrays.asList(as);
        Collections.sort(alist);

        String[] ts = t.split("");
        List<String> tlist = Arrays.asList(ts);
        Collections.sort(tlist);

        return alist.equals(tlist);
    }

}

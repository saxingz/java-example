package org.saxing.java8;

import java.util.*;

public class SetTest2 {

    private static Map<String, Boolean> schoolLock = new LinkedHashMap<String, Boolean>() {
        private static final long serialVersionUID = -4107318667711775317L;

        {
            put("1-base", false);           // 清除不依赖基础数据
            put("2-notice", false);         // 清除不依赖基础数据
            put("3-album", false);          // 清除不依赖基础数据
            put("4-moment", false);         // 清除不依赖基础数据
            put("5-cook", false);           // 清除不依赖基础数据
            put("6-website", false);        // 清除不依赖基础数据
            put("7-pctask", false);         // 清除不依赖基础数据
            put("8-plan", false);           // 清除不依赖基础数据
            put("9-finance", false);        // 依赖基础数据， 已手动修复
            put("10-attendance", false);    // 依赖学生数据，重跑可以解决
            put("11-xiaoetong", false);     // 清除不依赖基础数据
            put("12-loginlog", false);      // 清除不依赖基础数据
        }
    };

    public static void main(String[] args) {
        Set<String> keySet = schoolLock.keySet();
        // 先倒序删除，再正序迁移
        ArrayList<String> clearSort = new ArrayList<>(keySet);
        Collections.reverse(clearSort);
        for (String key : clearSort){
            System.out.println(key);
        }
    }

}

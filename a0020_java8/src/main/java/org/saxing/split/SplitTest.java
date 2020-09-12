package org.saxing.split;

import java.util.ArrayList;
import java.util.List;

public class SplitTest {

    public static String tempString = "";
    public static String split = "AA55";

    public static void main(String[] args) {
        int validLength = 24;
        boolean isPrefix = true;

//        List<String> list = new ArrayList<String>() {
//            private static final long serialVersionUID = -2239323048309774938L;
//            {
//                add("AA5505020300");
//                add("036067");
//                add("AA5505020300");
//                add("036067");
//                add("AA5505020300");
//                add("03494E");
//                add("AA5505020300");
//                add("03585F");
//                add("AA5505020300");
//                add("03585F");
//                add("AA5505020300");
//                add("033730");
//            }
//        };

        List<String> list = new ArrayList<String>() {
            private static final long serialVersionUID = -2239323048309774938L;
            {
                add("FF5100010021");
                add("DD51000100230C440E480E8CAA55");
                add("FF51000100240C410E480E8EAA55");
                add("FF51000100210C410E480E8BAA55");
                add("FF51000100220C430E480E8AAA55");
                add("FF51000100230C3F0E480EF7AA55");
                add("FF51000100230C350E480EFDAA55");
                add("FF51000300240CD30C480E1CAA55");
            }
        };
        list.forEach(str -> System.out.println(extract(str, validLength, isPrefix)));

//        System.out.println();
        // prefix valid 14 AA5505020300036067 -> 05020300036067
        // suffix valid 6  05020300036067AA55111111111  -> 05020300036067
//        System.out.println(startStop("067AA55", validLength, isPrefix));
    }

    /**
     * 取出有效数据
     *
     * @param info 数据片段
     * @param validLength 有效升序
     * @param isPrefix 是否是前缀
     * @return 有效数据（包含分割字符）
     */
    public static String extract(String info, int validLength, boolean isPrefix){
        tempString += info;
        // 防止溢出
        if (tempString.length() > 1000){
            tempString = tempString.substring(tempString.length() - 500);
        }
        int tempStringLength = tempString.length();
        // 开始/结束截取位置
        int startPosition, endPosition;
        String result = "";
        // 判断是否有分界
        int firstIndex = tempString.indexOf(split);
        if (firstIndex >= 0){
            startPosition = firstIndex;
            // 如果是开头截取类型（向后截取）
            if (isPrefix) {
                // 查找下一个分界
                int secondIndex = tempString.indexOf(split, startPosition + 1);
                if (secondIndex >= 0){
                    endPosition = secondIndex;
                    result = tempString.substring(startPosition + split.length(), endPosition);
                    result = result.length() < validLength ? "" : result.substring(0, validLength);
                    // 清掉取出的数据
                    tempString = tempString.substring(secondIndex);
                } else {
                    // 判断是否长度足够
                    if (validLength <= tempStringLength - firstIndex - split.length()){
                        result = tempString.substring(startPosition + split.length());
                        result = result.length() < validLength ? "" : result.substring(0, validLength);
                        // 清空当前缓存
                        tempString = "";
                    }
                }
            } else {
                // 如果是结尾截取类型（向前截取）
                if (firstIndex + 1 >= validLength){
                    // 取出之前的数据
                    result = tempString.substring(0, firstIndex);
                    result = result.length() < validLength ? "" : result.substring(result.length() - validLength);
                }
                // 清空 split 之前的数据
                tempString = tempString.substring(firstIndex + split.length());
            }
        }
        return result.length() > 0 ? isPrefix ? split + result : result + split : result;
    }


}

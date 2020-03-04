package org.saxing.xiutest;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * json test
 *
 * @author saxing 2020/2/25 23:31
 */
public class JsonTest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        System.out.println(StringUtils.isBlank(null));
        System.out.println(StringUtils.isEmpty(null));
        String tokenClientType = "null";
        tokenClientType = Objects.equals("null", tokenClientType) ? "" : tokenClientType;
        System.out.println(tokenClientType);

    }

    public static void main4(String[] args) {
        System.out.println(System.currentTimeMillis());
        LocalDateTime start = LocalDateTime.now();
        String startTimeFormat = start.format(DATE_TIME_FORMATTER);
        System.out.println(startTimeFormat);
    }

    public static void main2(String[] args) {
        String str = "{\"result\":{\"face_num\":1,\"face_list\":[{\"angle\":{\"roll\":-3.21,\"pitch\":14.67,\"yaw\":0.67},\"face_token\":\"b26bbcd559f6d7a587ce03ba4838e123\",\"location\":{\"top\":350.11,\"left\":107.07,\"rotation\":-4,\"width\":453,\"height\":461},\"face_probability\":1,\"age\":24}]},\"log_id\":3525556575152,\"error_msg\":\"SUCCESS\",\"cached\":0,\"error_code\":0,\"timestamp\":1578656013}";
        System.out.println(str);
    }

}

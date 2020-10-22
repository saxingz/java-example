package org.saxing.a0041_wemedia.domain.enums;

import lombok.Getter;

/**
 * 平台enum
 *
 * @author saxing 2020/10/18 17:17
 */
@Getter
public enum Platform {
    YOUTUBE("youtube", "youtube"),
    BILIBILI("bilibili", "哔哩哔哩"),
    DOUYU("douyu", "斗鱼"),
    XIMALAYA("ximalaya", "喜马拉雅"),
    XIGUASHIPING("xiguashiping", "西瓜视频"),
    DOUYIN("douyin", "抖音"),
    ;

    private final String name;
    private final String description;

    Platform(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

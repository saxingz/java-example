package org.saxing.a0041_wemedia.domain.enums;

import lombok.Getter;

/**
 * 平台enum
 *
 * @author saxing 2020/10/18 17:17
 */
@Getter
public enum DownLoadStatus {

    NOT_DOWNLOAD(0, "未下载"),
    DOWNLOADED(1, "已下载"),
    ;

    private final Integer code;
    private final String description;

    DownLoadStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}

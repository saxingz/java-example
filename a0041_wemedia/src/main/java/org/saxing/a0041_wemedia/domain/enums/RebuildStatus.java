package org.saxing.a0041_wemedia.domain.enums;

import lombok.Getter;

/**
 * 是否已重建
 *
 * @author saxing 2020/10/18 17:17
 */
@Getter
public enum RebuildStatus {

    NOT_REBUILD(0, "未重建"),
    REBUILDED(1, "已重建"),
    ;

    private final Integer code;
    private final String description;

    RebuildStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}

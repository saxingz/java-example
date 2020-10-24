package org.saxing.a0041_wemedia.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.saxing.a0041_wemedia.domain.entity.TransferDO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 原始视频表
 * </p>
 *
 * @author saxing
 * @since 2020-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="VideoVO对象", description="原始视频")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class VideoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "平台")
    private String platform;

    @ApiModelProperty(value = "频道id")
    private String channelId;

    @ApiModelProperty(value = "频道名称")
    private String channelTitle;

    @ApiModelProperty(value = "视频id")
    private String videoId;

    @ApiModelProperty(value = "视频标题")
    private String videoTitle;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "下载状态 0=未下载 1=已下载")
    private Integer downloadStatus;

    @ApiModelProperty(value = "转存后的url")
    private String downloadedUrl;

    @ApiModelProperty(value = "重建状态")
    private Integer rebuildStatus;

    @ApiModelProperty(value = "原视频上线时间")
    private Date publishTime;

    @ApiModelProperty(value = "缩略图")
    private String thumbnails;

    // 已迁移列表
    private List<TransferDO> transferList;

}

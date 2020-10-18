package org.saxing.a0041_wemedia.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@TableName("video")
@ApiModel(value="VideoDO对象", description="原始视频表")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class VideoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
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

    @ApiModelProperty(value = "原视频上线时间")
    private Date publishTime;

    @ApiModelProperty(value = "缩略图")
    private String thumbnails;

    @ApiModelProperty(value = "是否已删除")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;


}

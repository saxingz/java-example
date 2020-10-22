package org.saxing.a0041_wemedia.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 频道名称
 * </p>
 *
 * @author saxing
 * @since 2020-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("channel")
@ApiModel(value="ChannelDO对象", description="频道名称")
public class ChannelDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "频道名称")
    private String channelTitle;

    @ApiModelProperty(value = "频道id")
    private String channelId;

    @ApiModelProperty(value = "频道开始时间")
    private Date startTime;

    @ApiModelProperty(value = "追剧时间")
    private Date followTime;

    @ApiModelProperty(value = "是否已删除")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;


}

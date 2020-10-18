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
 * 搬运表
 * </p>
 *
 * @author saxing
 * @since 2020-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("transfer")
@ApiModel(value="TransferDO对象", description="搬运表")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TransferDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "平台")
    private String platform;

    @ApiModelProperty(value = "发布日期")
    private Date publishTime;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "搬运url")
    private String url;

    @ApiModelProperty(value = "是否已删除")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;


}

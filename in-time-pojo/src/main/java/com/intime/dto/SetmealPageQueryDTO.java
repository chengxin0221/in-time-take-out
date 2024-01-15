package com.intime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "分页查询时传递的数据模型")
public class SetmealPageQueryDTO {
    //页码
    @Schema(description = "页码")
    private int page;

    //每页记录数
    @Schema(description = "每页记录数")
    private int pageSize;

    //分类id
    @Schema(description = "分类id")
    private Integer categoryId;

    //套餐名称
    @Schema(description = "套餐名称")
    private String name;

    //套餐起售状态 0:停用 1:启用
    @Schema(description = "套餐起售状态 0:停用 1:启用")
    private Integer status;
}

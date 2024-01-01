package com.intime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "分类分页查询时传递的参数")
public class CategoryPageQueryDTO implements Serializable {
    //页码
    @Schema(description = "页码")
    private int page;

    //每页记录数
    @Schema(description = "每页记录数")
    private int pageSize;

    //分类名称
    @Schema(description = "分类名称")
    private String name;

    //分类类型 1 菜品分类  2 套餐分类
    @Schema(description = "分类类型 1 菜品分类  2 套餐分类")
    private Integer type;
}

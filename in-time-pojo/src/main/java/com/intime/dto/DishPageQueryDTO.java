package com.intime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class DishPageQueryDTO implements Serializable {
    //页码
    @Schema(description = "页码")
    private int page;

    //每页记录数
    @Schema(description = "每页记录数")
    private int pageSize;

    //菜品名称
    @Schema(description = "菜品名称")
    private String name;

    //分类id
    @Schema(description = "分类id")
    private Integer categoryId;

    //状态 0表示禁用 1表示启用
    @Schema(description = "状态 0表示禁用 1表示启用")
    private Integer status;
}

package com.intime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "新增、删除、修改分类时传递的参数")
public class CategoryDTO implements Serializable {
    //主键
    @Schema(description = "主键")
    private Long id;

    //类型: 1 菜品分类 2 套餐分类
    @Schema(description = "类型: 1 菜品分类 2 套餐分类")
    private Integer type;

    //分类名称
    @Schema(description = "分类名称")
    private String name;

    //顺序
    @Schema(description = "顺序")
    private Integer sort;
}

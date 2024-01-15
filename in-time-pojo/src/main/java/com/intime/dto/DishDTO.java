package com.intime.dto;

import com.intime.entity.DishFlavor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "新增、删除、修改菜品时传递的参数")
public class DishDTO implements Serializable {
    //菜品id
    @Schema(description = "菜品id")
    private Long id;
    //菜品名称
    @Schema(description = "菜品名称")
    private String name;
    //菜品分类id
    @Schema(description = "菜品分类id")
    private Long categoryId;
    //菜品价格
    @Schema(description = "菜品价格")
    private BigDecimal price;
    //图片
    @Schema(description = "图片")
    private String image;
    //描述信息
    @Schema(description = "描述信息")
    private String description;
    //0 停售 1 起售
    @Schema(description = "0 停售 1 起售")
    private Integer status;
    //口味
    @Schema(description = "口味")
    private List<DishFlavor> flavors = new ArrayList<>();
}

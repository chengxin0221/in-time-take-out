package com.intime.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.intime.entity.SetmealDish;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 套餐分页查询接口返回的数据格式
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "套餐分页查询接口返回的数据格式")
public class SetmealVO {
    @Schema(description = "id")
    private Long id;

    //分类id
    @Schema(description = "分类id")
    private Long categoryId;

    //套餐名称
    @Schema(description = "套餐名称")
    private String name;

    //套餐价格
    @Schema(description = "套餐价格")
    private BigDecimal price;

    //套餐起售状态 0:停用 1:启用
    @Schema(description = "套餐起售状态 0:停用 1:启用")
    private Integer status;

    //描述信息
    @Schema(description = "描述信息")
    private String description;

    //图片
    @Schema(description = "图片")
    private String image;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "分类名称")
    private String categoryName;

    //套餐和菜品的关联关系
    @Schema(description = "套餐和菜品的关联关系")
    private List<SetmealDish> setmealDishes = new ArrayList<>();
}

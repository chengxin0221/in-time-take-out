package com.intime.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetmealDish implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    //套餐id
    @Schema(description = "套餐id")
    private Long setmealId;

    //菜品id
    @Schema(description = "菜品id")
    private Long dishId;

    //菜品名称
    @Schema(description = "菜品名称")
    private String name;

    //菜品原价
    @Schema(description = "菜品原价")
    private BigDecimal price;

    //份数
    @Schema(description = "份数")
    private Integer copies;
}

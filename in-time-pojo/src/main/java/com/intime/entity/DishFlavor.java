package com.intime.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 菜品口味
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishFlavor implements Serializable {

    private static final long serialVersionUID = 1L;

    //菜品口味id
    @Schema(description = "菜品口味id")
    private Long id;
    //菜品id
    @Schema(description = "菜品id")
    private Long dishId;

    //口味名称
    @Schema(description = "口味名称")
    private String name;

    //口味数据list
    @Schema(description = "口味数据list")
    private String value;
}

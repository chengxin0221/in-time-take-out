package com.intime.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 菜品
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "主键")
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

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    //创建人
    private Long createUser;

    //修改人
    private Long updateUser;
}

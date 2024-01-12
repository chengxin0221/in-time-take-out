package com.intime.mapper;

import com.intime.annotation.AutoFill;
import com.intime.entity.DishFlavor;
import com.intime.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * 向口味表插入n条数据
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 通过id删除口味信息
     * @param dishId
     */
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * 根据菜品id查询口味数据
     * @param dish_id
     * @return
     */
    @Select("select * from dish_flavor where dish_id = #{dish_id}")
    List<DishFlavor> getByDishId(Long dish_id);
}

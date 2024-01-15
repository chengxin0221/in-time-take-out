package com.intime.service;

import com.intime.dto.SetmealDTO;
import com.intime.dto.SetmealPageQueryDTO;
import com.intime.entity.Setmeal;
import com.intime.result.PageResult;
import com.intime.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDTO
     */
    void addSetmealWithDish(SetmealDTO setmealDTO);

    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 批量删除套餐
     * @param ids
     */
    void deleteSetmeal(List<Long> ids);

    /**
     * 通过套餐id查询套餐
     * @param id
     * @return
     */
    SetmealVO getByIdWithDish(Long id);

    /**
     * 修改套餐信息
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 起售或停售套餐
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}

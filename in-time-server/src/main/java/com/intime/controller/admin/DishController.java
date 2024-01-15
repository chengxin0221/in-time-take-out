package com.intime.controller.admin;

import com.intime.dto.DishDTO;
import com.intime.dto.DishPageQueryDTO;
import com.intime.entity.Dish;
import com.intime.result.PageResult;
import com.intime.result.Result;
import com.intime.service.DishService;
import com.intime.vo.DishVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品管理
 */
@Slf4j
@RestController
@RequestMapping("/admin/dish")
@Tag(name = "菜品管理")
public class DishController {
    @Autowired
    private DishService dishService;

    /**
     * 新增菜品和对应的口味
     * @param dishDTO
     * @return
     */
    @Transactional
    @PostMapping
    @Operation(summary = "新增菜品和对应的口味")
    public Result<String> addDishWithFlavor(@RequestBody DishDTO dishDTO){
        log.info("新增菜品：{}", dishDTO);
        dishService.addDishWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品分页查询：{}",dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 菜品批量删除
     * @param ids
     * @return
     */
    @Transactional
    @DeleteMapping
    @Operation(summary = "菜品批量删除")
    //请求参数名与形参集合对象名相同且请求参数为多个，@RequestParam 绑定参数关系。
    public Result<String> delete(@RequestParam List<Long> ids){
        log.info("菜品批量删除: {}",ids);
        dishService.deleteDish(ids);
        return Result.success();
    }

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据id查询菜品")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("根据id查询菜品: {}", id);
        DishVO dishVO = dishService.getById(id);
        return Result.success(dishVO);
    }

    /**
     * 修改菜品信息
     * @param dishDTO
     * @return
     */
    @Transactional
    @PutMapping
    @Operation(summary = "修改菜品信息")
    public Result<String> update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品信息: {}",dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * 根据分类id查询菜品信息
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @Operation(summary = "根据分类id查询菜品信息")
    public Result<List<Dish>> list(Long categoryId){
        log.info("根据分类id查询菜品信息: {}",categoryId);
        List<Dish> dishes = dishService.list(categoryId);
        return Result.success(dishes);
    }
}

package com.intime.controller.admin;

import com.intime.dto.SetmealDTO;
import com.intime.dto.SetmealPageQueryDTO;
import com.intime.entity.Setmeal;
import com.intime.result.PageResult;
import com.intime.result.Result;
import com.intime.service.SetmealService;
import com.intime.vo.SetmealVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 套餐管理
 */
@Slf4j
@RestController
@RequestMapping("/admin/setmeal")
@Tag(name = "套餐管理")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * 新增套餐
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @Operation(summary = "新增套餐")
    public Result<String> addSetmeal(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐: {}", setmealDTO);
        setmealService.addSetmealWithDish(setmealDTO);
        return Result.success();
    }

    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "套餐分页查询")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("套餐分页查询: {}", setmealPageQueryDTO);
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 批量删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    @Operation(summary = "批量删除套餐")
    //请求参数名与形参集合对象名相同且请求参数为多个，@RequestParam 绑定参数关系。
    public Result<String> delete(@RequestParam List<Long> ids){
        log.info("批量删除套餐: {}", ids);
        setmealService.deleteSetmeal(ids);
        return Result.success();
    }

    /**
     * 通过套餐id查询套餐
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "通过套餐id查询套餐")
    public Result<SetmealVO> getById(@PathVariable Long id){
        log.info("通过套餐id查询套餐: {}", id);
        SetmealVO setmealVO = setmealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }

    /**
     * 修改套餐信息
     * @param setmealDTO
     * @return
     */
    @PutMapping
    @Operation(summary = "修改套餐信息")
    public Result<String> update(@RequestBody SetmealDTO setmealDTO){
        log.info("修改套餐信息: {}", setmealDTO);
        setmealService.update(setmealDTO);
        return Result.success();
    }

    /**
     * 起售或停售套餐
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @Operation(summary = "起售或停售套餐")
    public Result<String> startOrStop(@PathVariable Integer status, Long id){
        log.info("起售或停售套餐: {},{}", status, id);
        setmealService.startOrStop(status, id);
        return Result.success();
    }
}

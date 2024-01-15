package com.intime.controller.admin;

import com.intime.dto.CategoryDTO;
import com.intime.dto.CategoryPageQueryDTO;
import com.intime.entity.Category;
import com.intime.result.PageResult;
import com.intime.result.Result;
import com.intime.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类管理
 */
@Slf4j
@RestController
@RequestMapping("/admin/category")
@Tag(name = "分类管理")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    /**
     * 新增分类
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @Operation(summary = "新增分类")
    public Result<String> add(@RequestBody CategoryDTO categoryDTO) {
        log.info("新增分类：{}",categoryDTO);
        categoryService.add(categoryDTO);
        return Result.success();
    }

    /**
     * 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分页查询：{}",categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    @Operation(summary = "删除分类")
    @Parameter(name = "id", description = "id")
    public Result<String> deleteById(Long id){
        log.info("删除分类：{}", id);
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改分类
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @Operation(summary = "修改分类")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO){
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * 启用、禁用分类
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @Operation(summary = "启用禁用分类")
    @Parameter(name = "status", description = "分类状态 0 标识禁用 1 表示启用")
    @Parameter(name = "id", description = "id")
    public Result<String> startOrStop(@PathVariable("status") Integer status, Long id){
        categoryService.startOrStop(status,id);
        return Result.success();
    }

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    @GetMapping("/list")
    @Operation(summary = "根据类型查询分类")
    @Parameter(name = "type", description = "类型")
    public Result<List<Category>> list(Integer type){
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}

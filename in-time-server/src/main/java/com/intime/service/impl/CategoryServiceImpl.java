package com.intime.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intime.constan.MessageConstant;
import com.intime.constan.StatusConstant;
import com.intime.context.BaseContext;
import com.intime.dto.CategoryDTO;
import com.intime.dto.CategoryPageQueryDTO;
import com.intime.entity.Category;
import com.intime.exception.DeletionNotAllowedException;
import com.intime.mapper.CategoryMapper;
import com.intime.mapper.DishMapper;
import com.intime.mapper.SetmealMapper;
import com.intime.result.PageResult;
import com.intime.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;
    /**
     * 新增分类
     * @param categoryDTO
     */
    @Override
    public void add(CategoryDTO categoryDTO) {
        Category category = new Category();
        //属性拷贝
        BeanUtils.copyProperties(categoryDTO,category);
        //分类状态 0 标识禁用 1 表示启用
        category.setStatus(StatusConstant.DISABLE);

        /*在Mapper类中用@AutoFill注解通过AOP方式自动填充创建人、创建时间、修改人、修改时间*/

        categoryMapper.insert(category);
    }

    /**
     * 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        //使用 mybatis 的分页插件 PageHelper
        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 删除分类
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        //查询当前分类是否关联了菜品，如果关联了就抛出业务异常
        Integer count = dishMapper.countByCategoryId(id);
        if (count > 0){
            //当前分类下有菜品，不能删除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }
        //查询当前分类是否关联了套餐，如果关联了就抛出业务异常
        count = setmealMapper.countByCategoryId(id);
        if (count > 0){
            //当前分类下有菜品，不能删除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }
        //删除分类数据
        categoryMapper.deleteById(id);
    }

    /**
     * 修改分类
     * @param categoryDTO
     */
    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        /*在Mapper类中用@AutoFill注解通过AOP方式自动填充修改人、修改时间*/

        categoryMapper.update(category);
    }

    /**
     * 启用、禁用分类
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        Category category = Category.builder()
                .id(id)
                .status(status)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();
        categoryMapper.update(category);
    }

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    @Override
    public List<Category> list(Integer type) {
        return categoryMapper.list(type);
    }
}

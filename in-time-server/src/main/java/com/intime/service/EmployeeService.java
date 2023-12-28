package com.intime.service;

import com.intime.dto.EmployeeDTO;
import com.intime.dto.EmployeePageQueryDTO;
import com.intime.entity.Employee;
import com.intime.dto.EmployeeLoginDTO;
import com.intime.result.PageResult;

public interface EmployeeService {
    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO
     */
    void addUser(EmployeeDTO employeeDTO);


    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用或禁用员工账号
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 通过id获取员工信息
     * @param id
     * @return
     */
    Employee getById(Long id);

    /**
     * 编辑员工信息
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}

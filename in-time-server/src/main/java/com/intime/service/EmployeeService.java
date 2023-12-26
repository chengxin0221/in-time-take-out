package com.intime.service;

import com.intime.dto.EmployeeDTO;
import com.intime.entity.Employee;
import com.intime.dto.EmployeeLoginDTO;

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
}

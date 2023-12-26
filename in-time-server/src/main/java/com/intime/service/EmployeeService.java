package com.intime.service;

import com.intime.entity.Employee;
import com.intime.dto.EmployeeLoginDTO;

public interface EmployeeService {
    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);
}

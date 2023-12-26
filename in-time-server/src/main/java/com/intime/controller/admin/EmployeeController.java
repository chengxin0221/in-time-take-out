package com.intime.controller.admin;

import com.intime.constan.JwtClaimsConstant;
import com.intime.dto.EmployeeDTO;
import com.intime.dto.EmployeeLoginDTO;
import com.intime.entity.Employee;
import com.intime.properties.JwtProperties;
import com.intime.result.Result;
import com.intime.service.EmployeeService;
import com.intime.utils.JwtUtil;
import com.intime.vo.EmployeeLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * 员工管理
 */
@Slf4j
@RestController
@RequestMapping("/admin/employee")
@Tag(name = "员工管理")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     * @param employeeLoginDTO
     * @return
     */
    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        log.info("员工登录：{}",employeeLoginDTO);
        Employee employee = employeeService.login(employeeLoginDTO);

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        //jwt包含了当前登录的员工信息
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 新增员工
     * @param employeeDTO
     * @return
     */
    @Operation(summary = "新增员工")
    @PostMapping
    public Result addUser(@RequestBody EmployeeDTO employeeDTO){
        log.info("新增员工：{}",employeeDTO);
        employeeService.addUser(employeeDTO);
        return Result.success();
    }

    /**
     * 退出
     * @return
     */
    @Operation(summary = "退出")
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }
}

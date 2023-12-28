package com.intime.controller.admin;

import com.intime.constan.JwtClaimsConstant;
import com.intime.dto.EmployeeDTO;
import com.intime.dto.EmployeeLoginDTO;
import com.intime.dto.EmployeePageQueryDTO;
import com.intime.entity.Employee;
import com.intime.properties.JwtProperties;
import com.intime.result.PageResult;
import com.intime.result.Result;
import com.intime.service.EmployeeService;
import com.intime.utils.JwtUtil;
import com.intime.vo.EmployeeLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    @Operation(summary = "员工分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("分页查询：{}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用或禁用员工账号
     * @param status
     * @param id
     * @return
     */
    @Operation(summary = "启用或禁用员工账号")
    @PostMapping("/status/{status}")
    public Result<String> startOrStop(@PathVariable Integer status, Long id){
        log.info("启用或禁用员工账号：{}，{}",status,id);
        employeeService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 通过id获取员工信息
     * @param id
     * @return
     */
    @Operation(summary = "通过id获取员工信息")
    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Long id){
        log.info("通过id获取员工信息：{}",id);
        Employee employee = employeeService.getById(id);
        return Result.success();
    }

    /**
     * 编辑员工信息
     * @param employeeDTO
     * @return
     */
    @Operation(summary = "编辑员工信息")
    @PutMapping
    public Result<String> update(@RequestBody EmployeeDTO employeeDTO){
        log.info("编辑员工信息：{}",employeeDTO);
        employeeService.update(employeeDTO);
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

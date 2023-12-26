package com.intime.service.impl;

import com.intime.constan.MessageConstant;
import com.intime.constan.PasswordConstant;
import com.intime.constan.StatusConstant;
import com.intime.context.BaseContext;
import com.intime.dto.EmployeeDTO;
import com.intime.entity.Employee;
import com.intime.dto.EmployeeLoginDTO;
import com.intime.exception.AccountLockedException;
import com.intime.exception.AccountNotFoundException;
import com.intime.exception.PasswordErrorException;
import com.intime.mapper.EmployeeMapper;
import com.intime.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //进行md5加密，然后再进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 新增员工
     * @param employeeDTO
     */
    @Override
    public void addUser(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        //属性拷贝
        BeanUtils.copyProperties(employeeDTO,employee);
        //账号状态默认为1  1正常 0锁定
        employee.setStatus(StatusConstant.ENABLE);
        //默认密码为123456   进行md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        //创建人、创建时间、修改人、修改时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        //获取当前线程所对应的线程局部变量中保存的用户id
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());
        employeeMapper.insert(employee);
    }
}

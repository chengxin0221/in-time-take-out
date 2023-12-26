package com.intime.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    //实现Serializable接口的类，java虚拟机看到这个接口后，会为该类自动生成一个 序列化版本号
    //在类中手动编写固定不变的序列化版本号：private static final long serialVersionUID = 1L;
    private static final long serialVersionUID = 1L;
    //主键
    private Long id;
    //用户名
    private String username;
    //姓名
    private String name;
    //密码
    private String password;
    //手机号
    private String phone;
    //性别
    private String sex;
    //身份证号
    private String idNumber;
    //账号状态  1正常 0锁定
    private Integer status;
    //创建时间
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    //最后修改时间
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    //创建人id
    private Long createUser;
    //最后修改人id
    private Long updateUser;
}

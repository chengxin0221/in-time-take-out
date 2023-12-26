package com.intime.handler;

import com.intime.constan.MessageConstant;
import com.intime.exception.BaseException;
import com.intime.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestControllerAdvice   //表示当前类为全局异常处理器
public class GlobalExceptionHandler {
    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler   //指定可以捕获哪种类型的异常进行处理
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 捕获SQL异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error("异常信息：{}", ex.getMessage());
        String message = ex.getMessage();
        //用户名已存在
        if(message.contains("Duplicate entry")){
            //错误信息如：Duplicate entry 'zhangsan' for key 'employee.idx_username'
            String[] split = message.split(" ");
            //取出用户输入的用户名
            String name = split[2];
            return Result.error(name + MessageConstant.ALREADY_EXISTS);
        }
        //未知错误
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }
}

package com.intime.exception;

/**
 * 上传文件中七牛云Zone对象配置错误
 */
public class QiNiuZoneConfigException extends BaseException{
    public QiNiuZoneConfigException(){
    }

    public QiNiuZoneConfigException(String msg){
        super(msg);
    }
}

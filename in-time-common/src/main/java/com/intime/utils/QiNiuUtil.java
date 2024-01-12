package com.intime.utils;

import com.alibaba.fastjson.JSONObject;
import com.intime.constan.MessageConstant;
import com.intime.exception.QiNiuZoneConfigException;
import com.intime.properties.QiNiuProperties;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Component
@Slf4j
public class QiNiuUtil {

    @Autowired
    private QiNiuProperties qiNiuProperties;

    public String upload(MultipartFile localFile, String newFileName) throws IOException {
        Zone zone;
        if(qiNiuProperties.getZone().equals("zone0")){
            zone = Zone.zone0();
        }else if(qiNiuProperties.getZone().equals("zone1")){
            zone = Zone.zone1();
        }else if(qiNiuProperties.getZone().equals("zone2")){
            zone = Zone.zone2();
        }else if(qiNiuProperties.getZone().equals("zoneNa0")){
            zone = Zone.zoneNa0();
        }else if(qiNiuProperties.getZone().equals("zoneAs0")){
            zone = Zone.zoneAs0();
        }else{
            //Zone对象配置错误
            throw new QiNiuZoneConfigException(MessageConstant.Zone_Config_ERROR);
        }
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(zone);
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        Auth auth = Auth.create(qiNiuProperties.getAccessKey(), qiNiuProperties.getSecretKey());

        //指定不覆盖上传
        String upToken = auth.uploadToken(qiNiuProperties.getBucket());
        //put方法参数：本地文件字节数组，文件云端存储的名称，是否覆盖同名同位置文件
        Response response = uploadManager.put(localFile.getBytes(), newFileName, upToken);

        // 返回这张存储照片的地址
        String filePath = qiNiuProperties.getDomainOfBucket() + JSONObject.parseObject(response.bodyString()).get("key");
        log.info("上传的图片的存储路径：{}",filePath);
        return filePath;
    }
}

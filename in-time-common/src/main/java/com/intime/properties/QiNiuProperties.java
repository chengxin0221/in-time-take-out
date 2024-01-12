package com.intime.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "intime.qiniu")
@Data
public class QiNiuProperties {
    private String accessKey;
    private String secretKey;
    //存储空间名称
    private String bucket;
    //[{'zone0':'华东'}, {'zone1':'华北'},{'zone2':'华南'},{'zoneNa0':'北美'},{'zoneAs0':''}]
    private String zone;
    //外链默认域名，该空间的默认域名
    private String domainOfBucket;
}

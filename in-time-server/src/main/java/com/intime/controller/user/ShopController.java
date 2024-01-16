package com.intime.controller.user;

import com.intime.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("userShopController")//指定bean名称
@RequestMapping("/user/shop")
@Tag(name = "店铺操作相关接口")
public class ShopController {
    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询店铺营业状态
     * @return
     */
    @GetMapping("/status")
    @Operation(summary = "查询店铺营业状态")
    public Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("查询店铺营业状态为：{}", status == 1 ? "营业中" : "打烊中");
        return Result.success(status);
    }
}

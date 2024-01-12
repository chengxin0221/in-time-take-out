package com.intime.controller.admin;

import com.intime.constan.MessageConstant;
import com.intime.result.Result;
import com.intime.utils.QiNiuUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
@Tag(name = "通用接口")
@RequestMapping("/admin/common")
public class CommonController {

    @Autowired
    private QiNiuUtil qiNiuUtil;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @Operation(summary = "文件上传")
    public Result<String> upload(@RequestBody MultipartFile file, HttpServletRequest req){
        log.info("文件上传：{}", file);
        //获取原文件名
        String originalFilename = file.getOriginalFilename();
        //构建新的文件名
        String extname = originalFilename.substring(originalFilename.lastIndexOf("."));//文件扩展名
        String newFileName = UUID.randomUUID().toString() + extname;//随机名+文件扩展名
        log.info("新的文件名：{}",newFileName);

        try {
            //文件的请求路径
            String filePath = qiNiuUtil.upload(file, newFileName);
            return Result.success(filePath);
        } catch (Exception e) {
            log.error("文件上传失败：{}", e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}

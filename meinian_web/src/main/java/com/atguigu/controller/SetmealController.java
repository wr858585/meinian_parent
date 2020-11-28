package com.atguigu.controller;

import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.utils.QiniuUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @Author: oono
 * @Date: 2020/11/27
 * @Description:
 */

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    /**
     * 文件上传后端需要做三件事：
     * 1. 导入io和upload的jar包
     * 2. springmvc.xml中必须配置multipartResolver文件上传解析器
     * 3. 方法中形参类型必须位MultipartFile，形参名必须和前端保持一致，否则用@Param注解取别名在注解中保持一致
     * @param imgFile
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile){

        String fileName = UUID.randomUUID().toString().replace("-","") + imgFile.getOriginalFilename();

        try {
            QiniuUtils.upload2Qiniu(imgFile.getBytes(), fileName);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL,fileName);
        }
    };

}

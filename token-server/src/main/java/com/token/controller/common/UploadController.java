package com.token.controller.common;

import com.token.constant.MessageConstant;
import com.token.result.Result;
import com.token.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common/upload")
@Api(tags = "云端")
@Slf4j
public class UploadController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @Value(value = "${token.local.file.path}")
    private String localPath;

    /**
     * OSS存储文件
     *
     * @param file
     * @return
     */
    @PostMapping("/file")
    @ApiOperation(value = "OSS存储文件")
    public Result<String> file(MultipartFile file) {
        log.info("文件OSS存储:{}", file);
        try {
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件名的后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新文件名称
            String objectName = UUID.randomUUID() + extension;
            //文件的请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }

    /**
     * 本地存储文件
     *
     * @return
     */
    @PostMapping("/files")
    @ApiOperation(value = "本地存储文件")
    public Result<String> localFile(MultipartFile file) {
        log.info("文件本地存储:{}", file);
        File localFile = new File(localPath);
        // 判断当前服务器是否有该路径
        if (!localFile.exists()) {
            localFile.mkdirs();
        }
        //  获取原文件名
        String originalFilename = file.getOriginalFilename();
        //  获取文件后缀
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        //  重新构建文件名
        String fileName = UUID.randomUUID() + extension;
        try {
            file.transferTo(new File(localFile + "\\" + fileName));
            return Result.success(fileName);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}

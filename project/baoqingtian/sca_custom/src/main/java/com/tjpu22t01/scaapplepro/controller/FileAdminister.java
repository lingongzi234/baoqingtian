package com.tjpu22t01.scaapplepro.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.common.entity.User;
import com.tjpu22t01.scaapplepro.mapper.ApplicantMapper;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("logic/file")
public class FileAdminister {

    @Resource
    ApplicantMapper applicantMapper;

    private final MinioClient minioClient;

    public FileAdminister(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @PostMapping("applicantFile")
    public SaResult applicantIdCard(MultipartFile file, String usage) throws Exception {

        String userId = String.valueOf(((User) StpUtil.getSession().getDataMap().get("user")).getUserId());
//        String userId = "123";
        String fileName = usage + "/userId" + userId + ".jpg";
        try{
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket("tjpu22t01")
                    .object(fileName)
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .build();
            minioClient.putObject(args);
            return SaResult.ok().setData(fileName);
        }catch (Exception e){
            log.error(e.getMessage());
            return SaResult.error("文件上传失败");
        }
    }

}

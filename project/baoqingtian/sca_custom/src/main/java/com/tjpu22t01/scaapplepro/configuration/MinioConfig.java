package com.tjpu22t01.scaapplepro.configuration;

import io.minio.MinioClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@Slf4j
public class MinioConfig {

    @Value("${upload.minio.endpoint}")
    private String endpoint;

    @Value("${upload.minio.accessKey}")
    private String accessKey;

    @Value("${upload.minio.secretKey}")
    private String secretKey;

    @Value("${upload.minio.bucketName}")
    private String bucketName;

    @Value("{upload.minio.secure}")
    private String secure;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://106.3.99.64:20028")
                .credentials(accessKey, secretKey)
                .build();
        return minioClient;
    }
}

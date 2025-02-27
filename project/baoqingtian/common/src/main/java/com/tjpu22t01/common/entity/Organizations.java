package com.tjpu22t01.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@TableName("organizations")
public class Organizations {
    @Id
    private Integer organizationId;
    private String name;
    private String legalRepresentative;
    private String address;
    private String contactPerson;
    private String contactPhone;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

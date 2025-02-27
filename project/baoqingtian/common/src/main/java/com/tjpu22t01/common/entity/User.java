package com.tjpu22t01.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@TableName("users")
public class User {
    @Id
    private int userId;
    private String name;
    private String passwordHash;
    private String phoneNumber;
    private String email;
    private String identityType;
    private boolean status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int gender;
    private String idCard;
}

package com.tjpu22t01.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;
import java.sql.Date;

@Data
@TableName("mediators")
public class Mediators {

  @Id
  private long mediatorId;
  private String name;
  private String phoneNumber;
  private String email;
  private long organizationId;
  private String expertise;
  private long experienceYears;
  private boolean status;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;
  private String idCardNumber;
  private Date bornTime;
  private String educationLevel;
  private String employer;
  private int type;
  private String workExperience;
  private String qualifications;
  private String areasOfExpertise;
  private boolean gender;
}

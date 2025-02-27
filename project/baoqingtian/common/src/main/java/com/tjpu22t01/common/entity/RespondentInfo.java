package com.tjpu22t01.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import javax.persistence.Id;
import java.sql.Timestamp;
@Data
@TableName("respondent_info")
public class RespondentInfo {
  @Id
  private long respondentId;
  private long userId;
  private String companyName;
  private String jobTitle;
  private long creditScore;
  private String address;
  private double income;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;


}

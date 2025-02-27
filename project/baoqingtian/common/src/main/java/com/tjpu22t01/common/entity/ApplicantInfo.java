package com.tjpu22t01.common.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;

@TableName("applicant_info")
@Data
public class ApplicantInfo {

  @Id
  private long applicantId;

  private long userId;
  private String organizationName;
  private String idCardNumber;
  private String contactPhone;
  private java.sql.Date applicationDate;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;
  private String profession;
  private String idPhotoAddress;
  private String employmentCertificateAddress;
  private String powerAttorney;
  private String lawyerNumber;
  private String lawFirm;
  private String lawyerAddress;
  private String lawAddress;
}

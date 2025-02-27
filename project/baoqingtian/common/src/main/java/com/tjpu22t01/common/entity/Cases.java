package com.tjpu22t01.common.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;

@Data
@TableName("cases")
public class Cases {
      @Id
      private long caseId;
      private String respondentName;
      private long applicantId;
      private Long respondentId;
      private Long mediatorId;
      private Long organizationId;
      private double totalAmount;
      private double principalAmount;
      private double interestAmount;
      private double penaltyAmount;
      private double overdueInterest;
      private long status;
      private long mediationStatus;
      private java.sql.Timestamp createdAt;
      private java.sql.Timestamp updatedAt;
      private String caseReason;
      private String respondentIdNumber;
      private String appointmentTime;
}

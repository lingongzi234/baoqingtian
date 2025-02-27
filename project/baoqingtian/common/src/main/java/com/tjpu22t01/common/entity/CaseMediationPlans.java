package com.tjpu22t01.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@TableName("case_mediation_plans")
public class CaseMediationPlans {
    @Id
    private Integer planId;
    private Integer caseId;
    private Integer createdBy;
    private String planDetails;
    private String status;
    private Timestamp created_at;
    private Timestamp updated_at;
    private int casePlan;
}

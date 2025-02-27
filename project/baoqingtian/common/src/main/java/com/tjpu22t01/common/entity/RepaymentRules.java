package com.tjpu22t01.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@TableName("repayment_rules")
public class RepaymentRules {
    @Id
    private Integer ruleId;
    private String ruleName;
    private String ruleType;
    private String details;
    private Integer createdBy;
    private Timestamp createdAt;
    private String status;
}

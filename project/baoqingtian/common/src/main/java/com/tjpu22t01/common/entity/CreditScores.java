package com.tjpu22t01.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@TableName("credit_scores")
public class CreditScores {
    @Id
    private Integer scoreId;
    private Integer userId;
    private Integer score;
    private String changeReason;
    private Timestamp updatedAt;
}

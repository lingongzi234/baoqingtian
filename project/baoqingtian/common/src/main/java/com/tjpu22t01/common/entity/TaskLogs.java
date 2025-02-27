package com.tjpu22t01.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@TableName("task_logs")
public class TaskLogs {
    @Id
    private Integer logId;
    private String taskType;
    private Integer userId;
    private Integer relatedCaseId;
    private String details;
    private Timestamp createdAt;
}

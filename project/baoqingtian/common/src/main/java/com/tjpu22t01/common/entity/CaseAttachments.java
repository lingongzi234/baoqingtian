package com.tjpu22t01.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@TableName("case_attachments")
public class CaseAttachments {
    @Id
    private Integer attachmentId;

    private Integer caseId;
    private String filePath;
    private Integer uploadBy;
    private Timestamp uploadTime;
}

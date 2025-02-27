# tjpu22t00

#### 人员分工
- 桂铭: 5.组织管理, 7.数据统计, 8.个人资料管理（调解员）, 9.案件管理（调解员）
- 李松: 14.数据统计（申请人）, 15.个人管理（被申请人）, 16.案件管理（被申请人）
- 冯志鹏: 1.注册登录, 4.用户管理（模块）, 6.案件管理（管理员）, 11.个人资料（申请人）
- 殷文乐: 2.首页（管理员）, 3.首页（调解员）, 12.案件管理（申请人）

#### 数据库设计
```sql
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    identity_type ENUM('申请人', '被申请人', '管理员', '调解员') NOT NULL,
    status ENUM('启用', '停用') DEFAULT '启用',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

```sql
CREATE TABLE cases (
    case_id INT AUTO_INCREMENT PRIMARY KEY,
    case_name VARCHAR(255) NOT NULL,
    applicant_id INT NOT NULL,
    respondent_id INT NOT NULL,
    mediator_id INT DEFAULT NULL,
    organization_id INT DEFAULT NULL,
    total_amount DECIMAL(15, 2) NOT NULL,
    principal_amount DECIMAL(15, 2),
    interest_amount DECIMAL(15, 2),
    penalty_amount DECIMAL(15, 2),
    overdue_interest DECIMAL(15, 2),
    status ENUM('未申请', '已申请', '调解中', '已完结') NOT NULL,
    mediation_status ENUM('待处理', '调解成功', '调解失败') DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (applicant_id) REFERENCES users(user_id),
    FOREIGN KEY (respondent_id) REFERENCES users(user_id),
    FOREIGN KEY (mediator_id) REFERENCES mediators(mediator_id),
    FOREIGN KEY (organization_id) REFERENCES organizations(organization_id)
);

```

```sql
CREATE TABLE mediators (
    mediator_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(255),
    organization_id INT NOT NULL,
    expertise TEXT,
    experience_years INT,
    status ENUM('启用', '停用') DEFAULT '启用',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (organization_id) REFERENCES organizations(organization_id)
);

```

```sql
CREATE TABLE organizations (
    organization_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    legal_representative VARCHAR(255),
    address TEXT,
    contact_person VARCHAR(255),
    contact_phone VARCHAR(20),
    status ENUM('启用', '停用') DEFAULT '启用',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

```

```sql
CREATE TABLE repayment_rules (
    rule_id INT AUTO_INCREMENT PRIMARY KEY,
    rule_name VARCHAR(255) NOT NULL,
    rule_type ENUM('一次性还款', '等额分期', '阶段还款') NOT NULL,
    details TEXT NOT NULL,
    created_by INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('启用', '停用') DEFAULT '停用',
    FOREIGN KEY (created_by) REFERENCES users(user_id)
);

```

```sql
CREATE TABLE case_mediation_plans (
    plan_id INT AUTO_INCREMENT PRIMARY KEY,
    case_id INT NOT NULL,
    created_by INT NOT NULL,
    plan_details TEXT NOT NULL,
    status ENUM('待审核', '审核通过', '审核驳回') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (case_id) REFERENCES cases(case_id),
    FOREIGN KEY (created_by) REFERENCES mediators(mediator_id)
);

```

```sql
CREATE TABLE case_attachments (
    attachment_id INT AUTO_INCREMENT PRIMARY KEY,
    case_id INT NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    uploaded_by INT NOT NULL,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (case_id) REFERENCES cases(case_id),
    FOREIGN KEY (uploaded_by) REFERENCES users(user_id)
);

```

```sql
CREATE TABLE credit_scores (
    score_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    score INT NOT NULL,
    change_reason TEXT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

```

```sql
CREATE TABLE task_logs (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    task_type ENUM('审批', '调解', '案件处理') NOT NULL,
    user_id INT NOT NULL,
    related_case_id INT,
    details TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (related_case_id) REFERENCES cases(case_id)
);

```

#### 介绍
天津工业大学22级 大三 实训

#### 软件架构
软件架构说明


#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)

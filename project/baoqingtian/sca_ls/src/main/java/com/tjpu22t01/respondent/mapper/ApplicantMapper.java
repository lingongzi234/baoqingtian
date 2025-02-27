package com.tjpu22t01.respondent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjpu22t01.common.entity.ApplicantInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ApplicantMapper extends BaseMapper<ApplicantInfo>{
}

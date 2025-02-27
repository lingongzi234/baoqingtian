package com.tjpu22t01.scaapplepro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjpu22t01.common.entity.CaseMediationPlans;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CaseMediationPlanMapper extends BaseMapper<CaseMediationPlans> {
}

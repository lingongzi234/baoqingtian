package com.tjpu22t01.scaapplepro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjpu22t01.common.entity.Mediators;
import com.tjpu22t01.scaapplepro.controller.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdministratorMapper extends BaseMapper<Mediators> {

    @Update("UPDATE cases set mediator_id = #{mediatorId}, status = 2, mediation_status = 2 WHERE case_id = #{caseId}")
    Integer update(@Param("caseId") String caseId, @Param("mediatorId") String mediatorId);



}

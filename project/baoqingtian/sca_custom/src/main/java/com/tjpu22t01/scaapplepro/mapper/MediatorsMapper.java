package com.tjpu22t01.scaapplepro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjpu22t01.common.entity.Mediators;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MediatorsMapper extends BaseMapper<Mediators> {

    @Select("SELECT mediators.mediator_id FROM mediators WHERE user_id = #{userId};")
    Integer selectMediatorIdByUserId(Integer userId);

}

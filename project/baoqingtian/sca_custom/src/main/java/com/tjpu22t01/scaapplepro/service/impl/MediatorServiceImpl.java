package com.tjpu22t01.scaapplepro.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjpu22t01.common.entity.Cases;
import com.tjpu22t01.common.entity.User;
import com.tjpu22t01.scaapplepro.mapper.CasesMapper;
import com.tjpu22t01.scaapplepro.mapper.MediatorsMapper;
import com.tjpu22t01.scaapplepro.service.MediatorService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.List;

@Service
@Slf4j
public class MediatorServiceImpl implements MediatorService {

    @Resource
    MediatorsMapper mediatorsMapper;

    @Resource
    CasesMapper casesMapper;

    @Override
    public SaResult getCases(String status) {

//        Page<Cases> page = new Page<>(Long.valueOf(nowpage), Long.par);

        Integer userId = ((User) StpUtil.getSession().getDataMap().get("User")).getUserId();
        try{
            Integer mediatorID = mediatorsMapper.selectMediatorIdByUserId(userId);
            QueryWrapper<Cases> wrapper = new QueryWrapper<Cases>()
                    .eq("mediator_id", mediatorID)
                    .eq("status", status);
            List<Cases> cases = casesMapper.selectList(wrapper);
            return SaResult.ok().setData(cases);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
}

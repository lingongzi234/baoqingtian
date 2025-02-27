package com.tjpu22t01.respondent.service.impl;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tjpu22t01.common.entity.Cases;
import com.tjpu22t01.common.entity.RespondentInfo;
import com.tjpu22t01.respondent.controller.RespondentCases;
import com.tjpu22t01.respondent.mapper.CaseMapper;
import com.tjpu22t01.respondent.mapper.RespondentMapper;
import com.tjpu22t01.respondent.service.RespondentCasesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class RespondentCasesServiceImpl implements RespondentCasesService {
    @Resource
    private CaseMapper casesMapper ;
    @Resource
    private RespondentMapper respondentMapper;
    @Override
    public SaResult getcase(Long id) {
        log.info("get case");
        Wrapper<Cases> casesWrapper = new QueryWrapper<Cases>()
                .eq("respondent_id",id)
                .eq("mediation_status",2);
        List<Cases> cases = casesMapper.selectList(casesWrapper);
        return SaResult.ok().setData(cases);
    }
    @Override
    public SaResult getinformation(long case_id) {
        log.info("get information");
        Wrapper<Cases> casesWrapper =new QueryWrapper<Cases>().eq("case_id",case_id);
        List<Cases> cases=casesMapper.selectList(casesWrapper);
        return SaResult.ok().setData(cases);
    }
}
